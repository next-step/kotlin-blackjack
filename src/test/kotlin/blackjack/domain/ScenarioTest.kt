package blackjack.domain

import blackjack.domain.model.Card
import blackjack.domain.model.CardType
import blackjack.domain.model.CardValue
import blackjack.domain.model.Cards
import blackjack.domain.model.Dealer
import blackjack.domain.model.Money
import blackjack.domain.model.Player
import blackjack.domain.model.Trump
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class ScenarioTest {
    @Test
    fun `딜러가 지면 플레이어는 베팅 금액을 돌려받는다`() {
        val trump = Trump()

        val playerCard = Cards(
            cards = listOf(
                Card.from(CardType.SPADE, CardValue.KING),
                Card.from(CardType.HEART, CardValue.QUEEN),
            ), trump
        )

        val dealerCard = Cards(
            cards = listOf(
                Card.from(CardType.SPADE, CardValue.SEVEN),
                Card.from(CardType.CLOVER, CardValue.QUEEN),
            ), trump
        )

        val player = Player(trump, cards = playerCard)
        val dealer = Dealer(trump, cards = dealerCard)

        val battingMoney = Money(10000)
        player.batting(dealer, battingMoney)
        dealer.settleMoney(player)

        player.info.result.profit shouldBe battingMoney
    }

    @Test
    fun `딜러와 비기면 플레이어는 베팅 금액을 돌려받는다`() {
        val trump = Trump()

        val playerCard = Cards(
            cards = listOf(
                Card.from(CardType.SPADE, CardValue.SEVEN),
                Card.from(CardType.CLOVER, CardValue.QUEEN),
            ), trump
        )

        val dealerCard = Cards(
            cards = listOf(
                Card.from(CardType.SPADE, CardValue.TWO),
                Card.from(CardType.HEART, CardValue.QUEEN),
                Card.from(CardType.DIAMOND, CardValue.FIVE),
            ), trump
        )

        val player = Player(trump, cards = playerCard)
        val dealer = Dealer(trump, cards = dealerCard)

        val battingMoney = Money(10000)
        player.batting(dealer, battingMoney)
        dealer.settleMoney(player)

        player.info.result.profit shouldBe battingMoney
    }

    @Test
    fun `딜러의 카드의 합이 21을 넘어가면 플레이어의 카드와 상관없이 베팅 금액을 돌려받는다 - 플레이어 카드 21 미만`() {
        val trump = Trump()

        val playerCard = Cards(
            cards = listOf(
                Card.from(CardType.SPADE, CardValue.SEVEN),
                Card.from(CardType.HEART, CardValue.FIVE),
            ), trump
        )

        val dealerCard = Cards(
            cards = listOf(
                Card.from(CardType.SPADE, CardValue.QUEEN),
                Card.from(CardType.HEART, CardValue.QUEEN),
                Card.from(CardType.DIAMOND, CardValue.FIVE),
            ), trump
        )

        val player = Player(trump, cards = playerCard)
        val dealer = Dealer(trump, cards = dealerCard)

        val battingMoney = Money(10000)
        player.batting(dealer, battingMoney)
        dealer.settleMoney(player)

        player.info.result.profit shouldBe battingMoney
    }

    @Test
    fun `딜러의 카드의 합이 21을 넘어가면 플레이어의 카드와 상관없이 베팅 금액을 돌려받는다 - 플레이어 카드 21 초과`() {
        val trump = Trump()

        val playerCard = Cards(
            cards = listOf(
                Card.from(CardType.SPADE, CardValue.SEVEN),
                Card.from(CardType.CLOVER, CardValue.QUEEN),
                Card.from(CardType.HEART, CardValue.FIVE),
            ), trump
        )

        val dealerCard = Cards(
            cards = listOf(
                Card.from(CardType.SPADE, CardValue.QUEEN),
                Card.from(CardType.HEART, CardValue.QUEEN),
                Card.from(CardType.DIAMOND, CardValue.FIVE),
            ), trump
        )

        val player = Player(trump, cards = playerCard)
        val dealer = Dealer(trump, cards = dealerCard)

        val battingMoney = Money(10000)
        player.batting(dealer, battingMoney)
        dealer.settleMoney(player)

        player.info.result.profit shouldBe battingMoney
    }

    @Test
    fun `플레이어 카드가 2장이고 블랙잭으로 승리했을 때 플레이어는 베팅 금액의 150%를 딜러에게 받는다`() {
        val trump = Trump()

        val playerCard = Cards(
            cards = listOf(
                Card.from(CardType.SPADE, CardValue.ACE),
                Card.from(CardType.CLOVER, CardValue.QUEEN),
            ), trump
        )

        val dealerCard = Cards(
            cards = listOf(
                Card.from(CardType.SPADE, CardValue.QUEEN),
                Card.from(CardType.HEART, CardValue.QUEEN),
                Card.from(CardType.DIAMOND, CardValue.FIVE),
            ), trump
        )

        val player = Player(trump, cards = playerCard)
        val dealer = Dealer(trump, cards = dealerCard)

        player.batting(dealer, Money(10000))
        dealer.settleMoney(player)

        player.info.result.profit shouldBe Money((10000 * 1.5).toInt())
    }

    @Test
    fun `플레이어 카드의 합이 21을 넘어가서 패배하면 플레이어는 배팅 금액을 잃는다`() {
        val trump = Trump()

        val playerCard = Cards(
            cards = listOf(
                Card.from(CardType.SPADE, CardValue.SIX),
                Card.from(CardType.CLOVER, CardValue.QUEEN),
                Card.from(CardType.DIAMOND, CardValue.SIX),
            ), trump
        )

        val dealerCard = Cards(
            cards = listOf(
                Card.from(CardType.SPADE, CardValue.QUEEN),
                Card.from(CardType.HEART, CardValue.QUEEN),
            ), trump
        )

        val player = Player(trump, cards = playerCard)
        val dealer = Dealer(trump, cards = dealerCard)

        player.batting(dealer, Money(10000))
        dealer.settleMoney(player)

        player.info.result.profit shouldBe Money(-10000)
    }

    @Test
    fun `플레이어와 딜러 카드의 합이 각각 21을 넘어가지 않고 딜러가 승리하면 플레이어는 배팅 금액을 잃는다`() {
        val trump = Trump()

        val playerCard = Cards(
            cards = listOf(
                Card.from(CardType.SPADE, CardValue.SIX),
                Card.from(CardType.CLOVER, CardValue.QUEEN),
            ), trump
        )

        val dealerCard = Cards(
            cards = listOf(
                Card.from(CardType.SPADE, CardValue.QUEEN),
                Card.from(CardType.HEART, CardValue.QUEEN),
            ), trump
        )

        val player = Player(trump, cards = playerCard)
        val dealer = Dealer(trump, cards = dealerCard)

        player.batting(dealer, Money(10000))
        dealer.settleMoney(player)

        player.info.result.profit shouldBe Money(-10000)
    }
}
