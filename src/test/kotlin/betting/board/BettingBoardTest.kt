package betting.board

import betting.Bet
import betting.BetResult
import blackjack.card.Card
import blackjack.card.CardFixture
import blackjack.dealer.Dealer
import blackjack.participant.Participant
import blackjack.player.Hand
import blackjack.player.Player
import blackjack.player.Players
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class BettingBoardTest {
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
    fun `플레이어가 패배한 경우 베팅한 금액을 잃고 딜러가 베팅금을 얻는다`() {
        BettingBoard.winDealer(player = pobi, dealer = dealer)
        dealer.betResult.winning.amount shouldBe pobi.betAmount
    }

    @Test
    fun `딜러가 패배하는 경우 모든 플레이어는 베팅 금액을 받는다`() {
        BettingBoard.winRemainedPlayer(players = Players(players = listOf(pobi, jason)), dealer = dealer)

        dealer.betResult.winning.amount shouldBe sumOfPlayerBetsWithNegative()
        players.forAll { it.betAmount shouldBe it.betResult.winning.amount }
    }

    @Test
    @DisplayName("플레이어의 처음 2장의 합이 21이고, 딜러는 아닌 경우 플레이어는 베팅 금액의 1.5배를 받는다")
    fun playerIsBlackjackButDealerIsNotBlackjack() {
        players = players.map { it.hitCards(CardFixture.generateBlackJack()) as Player }

        BettingBoard.handleBlackjack(players = Players(players = players), dealer = dealer)

        players.forAll { it.isBlackjack() shouldBe true }
        dealer.isBlackjack() shouldBe false
        dealer.betResult.winning.amount shouldBe sumOfPlayerBetsWithNegative().times(BettingBoard.BONUS_RATIO)
    }

    @Test
    @DisplayName("플레이어의 처음 2장의 합이 21이고, 딜러도 21인 경우 플레이어는 베팅 금액을 돌려 받는다")
    fun playerAndDealerIsBlackjack() {
        players = players.map { it.hitCards(CardFixture.generateBlackJack()) as Player }
        dealer = dealer.hitCards(CardFixture.generateBlackJack()) as Dealer

        BettingBoard.handleBlackjack(players = Players(players = players), dealer = dealer)

        players.forAll { it.isBlackjack() shouldBe true }
        dealer.isBlackjack() shouldBe true
        dealer.betResult.winning.amount shouldBe sumOfPlayerBetsWithNegative()
    }

    private fun sumOfPlayerBetsWithNegative() = players.sumOf { it.bet.negative() }

    private fun Participant<*>.hitCards(cards: List<Card>): Participant<*> {
        return when (this) {
            is Player -> Player(name = this.name, hand = Hand(cards = this.hand.cards.plus(cards)))
            is Dealer -> Dealer(name = this.name, hand = Hand(cards = this.hand.cards.plus(cards)))
            else -> throw IllegalArgumentException("Unsupported participant type")
        }
    }
}
