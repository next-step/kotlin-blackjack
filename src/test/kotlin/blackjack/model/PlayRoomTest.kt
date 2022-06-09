package blackjack.model

import blackjack.dummy.toCardSet
import blackjack.model.card.Card
import blackjack.model.card.State
import blackjack.model.player.CardRecipient
import blackjack.model.player.HitDecisionMaker
import blackjack.model.player.Player
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

        this.dealer = Player.Dealer("딜러")
        this.alwaysHitDecisionMaker = object : HitDecisionMaker {
            override fun doYouWantToHit(player: Player) = true
        }

        this.alwaysStayDecisionMaker = object : HitDecisionMaker {
            override fun doYouWantToHit(player: Player) = false
        }

        this.sequentialCardDistributor = object : CardDistributor {
            private var offset = 0
            override fun resetCardSet() {
                this.offset = 0
            }

            override fun giveCardsTo(recipient: CardRecipient, count: Int) {
                repeat(count) { recipient.addCard(Card.of(offset++)) }
            }
        }
    }

    @Test
    fun `playRoom startGame Test`() {

        // given
        val cardDistributor = sequentialCardDistributor.apply {
            this.resetCardSet()
        }
        val players = listOf(
            Player.Guest("a", alwaysHitDecisionMaker),
            Player.Guest("b", alwaysHitDecisionMaker),
            Player.Guest("c", alwaysHitDecisionMaker),
            Player.Guest("d", alwaysHitDecisionMaker),
        ).toPlayers()
            .onEach { it.hitWhileWants(cardDistributor = cardDistributor) }

        val initialCardCountOfEachPlayer = 2
        val playRoom = PlayRoom(cardDistributor, dealer, players, initialCardCountOfEachPlayer)

        // when
        playRoom.startNewGame()

        // then
        assertAll(
            {
                assertThat(players.map { it.cardCount }.distinct())
                    .containsOnly(initialCardCountOfEachPlayer)
            },
            { assertThat(players[0].state).isEqualTo(State.Running(12)) },
            { assertThat(players[1].state).isEqualTo(State.Running(12)) },
            { assertThat(players[2].state).isEqualTo(State.Running(4)) },
            { assertThat(players[3].state).isEqualTo(State.Running(4)) }
        )
    }

    @Test
    fun `Dealer가 Bust 되면 Guest 점수 상관 없이 모두 승 테스트`() {

        // given
        val cardDistributor = sequentialCardDistributor.apply {
            this.resetCardSet()
        }
        val cardForBust = "JS,JD,5S".toCardSet()

        val bustPlayers = listOf("a", "b", "c").map { Player.Guest("a", alwaysHitDecisionMaker) }
            .onEach { player -> cardForBust.forEach(player::addCard) }
            .toPlayers()

        val bustDealer = this.dealer.apply {
            cardForBust.forEach(this::addCard)
        }

        val initialCardCountOfEachPlayer = 2
        val playRoom = PlayRoom(cardDistributor, bustDealer, bustPlayers, initialCardCountOfEachPlayer)

        // when
        val playerRecords = playRoom.playGame()
        val dealerRecord = playerRecords.playerRecordList.find { it.player is Player.Dealer }

        // then
        assertAll(
            { assertThat(dealerRecord?.win).isEqualTo(0) },
            { assertThat(dealerRecord?.lose).isEqualTo(bustPlayers.count()) },
            { assertThat(dealerRecord?.draw).isEqualTo(0) },
            { assertThat(playerRecords.filter { it.win == 1 && it.lose == 0 && it.draw == 0 }).hasSize(bustPlayers.count()) }

        )
    }

    @Test
    fun `Running인 것에 대해 각각 딜러와 승패 판정 `() {

        // given
        val cardDistributor = sequentialCardDistributor.apply {
            this.resetCardSet()
        }

        // 딜러 : 15점 획득 2승 1패 1무 목표
        val dealer = this.dealer.apply {
            "5S,5D,5H".toCardSet().forEach(this::addCard)
        }

        // 버스트 패자 : 25점 획득 패
        val bustPlayer = Player.Guest("bustGuest", alwaysStayDecisionMaker).apply {
            "JS,JD,5S".toCardSet().forEach(this::addCard)
        }

        // 패자 : 13점 획득 패
        val loser = Player.Guest("loser", alwaysStayDecisionMaker).apply {
            "QS,3D".toCardSet().forEach(this::addCard)
        }

        // 승자 : 15점 동점자 무
        val drawPlayer = Player.Guest("drawGuest", alwaysStayDecisionMaker).apply {
            val cardForBust = "6S,5D,4S".toCardSet()
            cardForBust.forEach(this::addCard)
        }

        // 승자 : 20점 획득 승
        val winner = Player.Guest("winner", alwaysStayDecisionMaker).apply {
            val cardForBust = "JS,8D,2S".toCardSet()
            cardForBust.forEach(this::addCard)
        }

        val guests = listOf(bustPlayer, loser, winner, drawPlayer).toPlayers()
        val initialCardCountOfEachPlayer = 2
        val playRoom = PlayRoom(cardDistributor, dealer, guests, initialCardCountOfEachPlayer)

        // when
        val playerRecords = playRoom.playGame()
        val dealerRecord = playerRecords.playerRecordList.find { it.player === dealer }
        val bustPlayerRecord = playerRecords.playerRecordList.find { it.player === bustPlayer }
        val loserRecord = playerRecords.playerRecordList.find { it.player === loser }
        val winnerRecord = playerRecords.playerRecordList.find { it.player === winner }
        val drawRecord = playerRecords.playerRecordList.find { it.player === drawPlayer }

        // then
        assertAll(
            { assertThat(dealerRecord?.win).isEqualTo(2) },
            { assertThat(dealerRecord?.lose).isEqualTo(1) },
            { assertThat(dealerRecord?.draw).isEqualTo(1) },

            { assertThat(bustPlayerRecord?.win).isEqualTo(0) },
            { assertThat(bustPlayerRecord?.lose).isEqualTo(1) },
            { assertThat(bustPlayerRecord?.draw).isEqualTo(0) },

            { assertThat(loserRecord?.win).isEqualTo(0) },
            { assertThat(loserRecord?.lose).isEqualTo(1) },
            { assertThat(loserRecord?.draw).isEqualTo(0) },

            { assertThat(winnerRecord?.win).isEqualTo(1) },
            { assertThat(winnerRecord?.lose).isEqualTo(0) },
            { assertThat(winnerRecord?.draw).isEqualTo(0) },

            { assertThat(drawRecord?.win).isEqualTo(0) },
            { assertThat(drawRecord?.lose).isEqualTo(0) },
            { assertThat(drawRecord?.draw).isEqualTo(1) }
        )
    }
}
