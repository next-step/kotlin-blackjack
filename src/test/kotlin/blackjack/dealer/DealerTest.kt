package blackjack.dealer

import betting.Bet
import betting.BetResult
import blackjack.card.CardFixture
import blackjack.card.Rank
import blackjack.machine.BlackJackMachine.Companion.BONUS_RATIO
import blackjack.participant.ParticipantFixture.hitCards
import blackjack.player.Hand
import blackjack.player.Player
import io.kotest.inspectors.forAll
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.beInstanceOf
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class DealerTest {
    private lateinit var dealer: Dealer
    private lateinit var pobi: Player
    private lateinit var jason: Player
    private lateinit var players: List<Player>

    @BeforeEach
    fun setUp() {
        dealer = Dealer()
        pobi = Player(name = "pobi", betResult = BetResult.Default(bet = Bet(amount = 1_000.0)))
        jason = Player(name = "jason", betResult = BetResult.Default(bet = Bet(amount = 2_000.0)))
        players = listOf(pobi, jason)
    }

    @Test
    fun `딜러 객체 생성 테스트`() {
        val dealer = Dealer()

        dealer.name shouldBe "딜러"
        dealer.hand shouldBe Hand(cards = emptyList())
    }

    @Test
    fun `초기 패 상태를 전달하는 딜러 객체 생성 테스트`() {
        val initialCards =
            listOf(
                CardFixture.generateTestCard(rank = Rank.SIX),
                CardFixture.generateTestCard(rank = Rank.SEVEN),
            )

        val dealer =
            Dealer.ready(
                initialCards = initialCards,
            )

        dealer.hand shouldBe Hand(cards = initialCards)
    }

    @Test
    fun `딜러에 패 목록에 카드 추가 기능을 테스트한다`() {
        var dealer = Dealer()
        val drawnCard = CardFixture.generateTestCard(rank = Rank.TEN)
        dealer = dealer.hitCard(drawnCard)

        dealer.hand.cards shouldContainExactly listOf(drawnCard)
    }

    @Test
    fun `딜러의 패의 총 합이 21을 넘어가면 false를 반환한다`() {
        val dealer =
            Dealer(hand = Hand(
                cards = listOf(
                    CardFixture.generateTestCard(rank = Rank.SIX),
                    CardFixture.generateTestCard(rank = Rank.SEVEN),
                    CardFixture.generateTestCard(rank = Rank.TEN),
                    ),
                ),
        )

        dealer.isBust() shouldBe true
    }

    @Test
    fun `딜러의 초기 패의 총 합이 16이하면 카드를 더 받는다`() {
        val dealer =
            Dealer(hand = Hand(
                cards = listOf(
                    CardFixture.generateTestCard(rank = Rank.SIX),
                    CardFixture.generateTestCard(rank = Rank.SEVEN),
                    ),
                ),
        )

        dealer.shouldDraw() shouldBe true
    }

    @Test
    @DisplayName("플레이어의 처음 2장의 합이 21이고, 딜러는 아닌 경우 플레이어는 베팅 금액의 1.5배를 받는다")
    fun playerIsBlackjackButDealerIsNotBlackjack() {
        players = players.map { it.hitCards(CardFixture.generateBlackJack()) as Player }

        dealer = dealer.handleBlackJack(blackJackPlayers = players.filter { it.isBlackjack() })

        players.forAll { it.isBlackjack() shouldBe true }
        dealer.isBlackjack() shouldBe false
        dealer.winingAmount shouldBe sumOfPlayerBetsWithNegative().times(BONUS_RATIO)
    }

    @Test
    @DisplayName("플레이어의 처음 2장의 합이 21이고, 딜러도 21인 경우 플레이어는 베팅 금액을 돌려 받는다")
    fun playerAndDealerIsBlackjack() {
        players = players.map { it.hitCards(CardFixture.generateBlackJack()) as Player }
        dealer = dealer.hitCards(CardFixture.generateBlackJack()) as Dealer

        dealer = dealer.handleBlackJack(blackJackPlayers = players.filter { it.isBlackjack() })

        players.forAll { it.isBlackjack() shouldBe true }
        dealer.isBlackjack() shouldBe true
        dealer.winingAmount shouldBe sumOfPlayerBetsWithNegative()
    }

    @Test
    fun `플레이어가 패배한 경우 베팅한 금액을 딜러가 얻는다`() {
        dealer = dealer.win(player = pobi)
        dealer.betResult should beInstanceOf<BetResult.Win>()
        dealer.winingAmount shouldBe pobi.betAmount
    }

    private fun sumOfPlayerBetsWithNegative() = players.sumOf { it.bet.negative() }
}
