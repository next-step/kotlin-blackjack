package blackjack.model

import blackjack.dummy.toCardSet
import blackjack.fixture.AlwaysHitDecisionMaker
import blackjack.fixture.AlwaysStayDecisionMaker
import blackjack.model.card.Card
import blackjack.model.player.CardRecipient
import blackjack.model.player.HitDecisionMaker
import blackjack.model.player.Player
import blackjack.model.player.PlayerBet
import blackjack.model.player.PlayerBets.Companion.toPlayerBets
import blackjack.model.player.PlayerRecord
import blackjack.model.player.Players.Companion.toPlayers
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

internal class PlayRoomTest {

    private lateinit var alwaysHitDecisionMaker: HitDecisionMaker
    private lateinit var alwaysStayDecisionMaker: HitDecisionMaker
    private lateinit var sequentialCardDistributor: CardDistributor
    private lateinit var dealer: Player.Dealer

    @BeforeEach
    fun setUp() {

        this.alwaysHitDecisionMaker = AlwaysHitDecisionMaker
        this.alwaysStayDecisionMaker = AlwaysStayDecisionMaker

        this.sequentialCardDistributor = object : CardDistributor {
            private var offset = 0
            override fun resetCardSet() {
                this.offset = 0
            }

            override fun giveCardsTo(recipient: CardRecipient, count: Int) {
                repeat(count) { recipient.addCard(Card.of(offset++)) }
            }
        }

        this.dealer = Player.Dealer("딜러")
    }

    @Test
    fun `playRoom 에서 게임 시작시 초기 카드 장 설정값(2장)씩 배분하는지 테스트`() {

        // given
        val cardDistributor = sequentialCardDistributor.apply {
            this.resetCardSet()
        }
        val players = listOf(
            Player.Guest("a", alwaysHitDecisionMaker),
            Player.Guest("b", alwaysHitDecisionMaker),
            Player.Guest("c", alwaysHitDecisionMaker),
            Player.Guest("d", alwaysHitDecisionMaker),
        ).onEach { it.hitWhileWants(cardDistributor = cardDistributor) }

        val playerBets = players.map { PlayerBet(it) }.toPlayerBets()
        val playRoom = PlayRoom(cardDistributor, dealer, playerBets)
        val initialCardCountForEachPlayer = CardDistributor.INITIAL_CARD_COUNT_FOR_EACH_PLAYER

        // when
        playRoom.startNewGame()

        // then
        assertAll(
            { assertThat(players[0].cardCount).isEqualTo(initialCardCountForEachPlayer) },
            { assertThat(players[1].cardCount).isEqualTo(initialCardCountForEachPlayer) },
            { assertThat(players[2].cardCount).isEqualTo(initialCardCountForEachPlayer) },
            { assertThat(players[3].cardCount).isEqualTo(initialCardCountForEachPlayer) }
        )
    }

    @Test
    fun `Dealer가 Bust 되면 Guest 점수 상관 없이 모두 승 테스트`() {

        // given
        val cardDistributor = sequentialCardDistributor.apply {
            this.resetCardSet()
        }
        val cardForBust = "JS,JD,5S".toCardSet()
        val betMoney = PlayerBet.MIN_BET_MONEY

        val bustPlayers = listOf("a", "b", "c").map { Player.Guest("a", alwaysHitDecisionMaker) }
            .onEach { player -> cardForBust.forEach(player::addCard) }

        val bustPlayerBests = bustPlayers.map { PlayerBet(it) }.toPlayerBets()

        val bustDealer = this.dealer.apply {
            cardForBust.forEach(this::addCard)
        }

        val playRoom = PlayRoom(cardDistributor, bustDealer, bustPlayerBests)

        val expectedDealerRecord = PlayerRecord.DealerRecord(
            player = dealer,
            lose = bustPlayers.count(),
            earnMoney = -(betMoney * bustPlayers.count())
        )
        // when
        val playerRecords = playRoom.playGame()
        val dealerRecord = playerRecords.playerRecordList.find { it.player is Player.Dealer }

        // then
        assertAll(
            { assertThat(dealerRecord).isEqualTo(expectedDealerRecord) },
            { assertThat(playerRecords.filterIsInstance<PlayerRecord.GuestWin>()).hasSize(bustPlayers.count()) }

        )
    }

    @Test
    fun `Running인 것에 대해 각각 딜러와 승패 판정 `() {

        // given
        val betMoney = PlayerBet.MIN_BET_MONEY
        val cardDistributor = sequentialCardDistributor.apply {
            this.resetCardSet()
        }

        // 딜러 : 15점 획득 2승 1패 1무 목표
        val dealer = this.dealer.apply {
            "5S,5D,5H".toCardSet().forEach(this::addCard)
        }

        // 버스트 패자 : 25점 획득 패
        val bustPlayer = Player.Guest(
            name = "bustGuest",
            hitDecisionMaker = alwaysStayDecisionMaker
        ).apply { "JS,JD,5S".toCardSet().forEach(this::addCard) }

        // 패자 : 13점 획득 패
        val loser = Player.Guest(
            name = "loser",
            hitDecisionMaker = alwaysStayDecisionMaker
        ).apply { "QS,3D".toCardSet().forEach(this::addCard) }

        // 승자 : 15점 동점자 무
        val drawPlayer = Player.Guest(
            name = "drawGuest",
            hitDecisionMaker = alwaysStayDecisionMaker
        ).apply {
            val cardForBust = "6S,5D,4S".toCardSet()
            cardForBust.forEach(this::addCard)
        }

        // 승자 : 20점 획득 승
        val winner = Player.Guest(
            name = "winner",
            hitDecisionMaker = alwaysStayDecisionMaker
        ).apply {
            val cardForBust = "JS,8D,2S".toCardSet()
            cardForBust.forEach(this::addCard)
        }

        val guests = listOf(bustPlayer, loser, winner, drawPlayer).toPlayers()
        val playRoom = PlayRoom(cardDistributor, dealer, guests.map { PlayerBet(it) }.toPlayerBets())

        // when
        val playerRecords = playRoom.playGame()
        val dealerRecord = playerRecords.playerRecordList.find { it is PlayerRecord.DealerRecord }
        val bustPlayerRecord = playerRecords.playerRecordList.find { it.player === bustPlayer }
        val loserRecord = playerRecords.playerRecordList.find { it.player === loser }
        val winnerRecord = playerRecords.playerRecordList.find { it.player === winner }
        val drawRecord = playerRecords.playerRecordList.find { it.player === drawPlayer }

        val expectedWinCount = 2
        val expectedLoseCount = 1
        val expectedDrawCount = 1
        val expectedEarnMoney = betMoney * expectedWinCount - betMoney * expectedLoseCount

        val expectedDealerRecord =
            PlayerRecord.DealerRecord(
                dealer,
                win = expectedWinCount,
                lose = expectedLoseCount,
                draw = expectedDrawCount,
                earnMoney = expectedEarnMoney
            )
        // then
        assertAll(
            { assertThat(dealerRecord).isEqualTo(expectedDealerRecord) },
            { assertThat(bustPlayerRecord).isInstanceOf(PlayerRecord.GuestLose::class.java) },
            { assertThat(loserRecord).isInstanceOf(PlayerRecord.GuestLose::class.java) },
            { assertThat(winnerRecord).isInstanceOf(PlayerRecord.GuestWin::class.java) },
            { assertThat(drawRecord).isInstanceOf(PlayerRecord.GuestDraw::class.java) }
        )
    }
}
