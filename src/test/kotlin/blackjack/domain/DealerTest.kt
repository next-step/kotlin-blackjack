package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class DealerTest {

    @Test
    fun `딜러는 발급 받은 첫 카드만 공개한다`() {
        val dealer = Dealer(cardDeck(Card.diamond(Number.TEN), Card.heart(Number.FIVE)))

        val actual = dealer.openedCards

        assertThat(actual).isEqualTo(Card.diamond(Number.TEN))
    }

    @Test
    fun `딜러는 16점 이하인 경우 카드를 발급 받을 수 있다`() {
        val dealer = Dealer(cardDeck(Card.diamond(Number.TEN), Card.heart(Number.SIX), Card.spade(Number.ACE)))

        dealer.obtain()

        assertThat(dealer.hands).containsExactlyInAnyOrder(
            Card.diamond(Number.TEN), Card.heart(Number.SIX), Card.spade(Number.ACE)
        )
    }

    @Test
    fun `딜러는 17점 이상인 경우 카드를 발급 받을 수 없다`() {
        val dealer = Dealer(cardDeck(Card.diamond(Number.TEN), Card.heart(Number.SEVEN), Card.spade(Number.ACE)))

        assertThrows<IllegalArgumentException> { dealer.obtain() }
        assertThat(dealer.hands).containsExactlyInAnyOrder(
            Card.diamond(Number.TEN), Card.heart(Number.SEVEN)
        )
    }

    @ParameterizedTest
    @CsvSource(value = ["TEN, SIX, true", "TEN, SEVEN, false"])
    fun `딜러는 카드 발급 여부를 확인할 수 있다`(num1: Number, num2: Number, expect: Boolean) {
        val dealer = Dealer(cardDeck(Card.diamond(num1), Card.heart(num2)))

        assertThat(dealer.isObtainable()).isEqualTo(expect)
    }

    @ParameterizedTest
    @CsvSource(value = ["TEN, JACK, TWO", "ACE, FIVE, FIVE", "ACE, FOUR, FIVE"])
    fun `딜러가 21이 넘으면 플레이어는 항상 승리한다`(num1: Number, num2: Number, num3: Number) {
        // arrange
        val dealer = Dealer(
            cardDeck(Card.diamond(Number.TEN), Card.heart(Number.SIX), Card.spade(Number.SIX))
        ).apply { obtain() }
        val player = Player(
            "player",
            cardDeck(Card.spade(num1), Card.clover(num2), Card.heart(num3))
        ).apply { obtain() }

        // act
        val actual = dealer.compareWith(player)

        // assert
        assertThat(actual).isEqualTo(CompareResult.DEALER_LOSE)
    }

    @ParameterizedTest
    @CsvSource(value = ["TEN, TEN, DRAW", "TEN, ACE, DEALER_LOSE", "TEN, NINE, DEALER_WIN"])
    fun `딜러와 플레이어가 21이하면 21에 더 가까운 플레이어가 승리한다`(num1: Number, num2: Number, expect: CompareResult) {
        // arrange
        val dealer = Dealer(
            cardDeck(Card.diamond(Number.TEN), Card.heart(Number.TEN))
        )
        val player = Player(
            "player",
            cardDeck(Card.spade(num1), Card.clover(num2))
        )

        // act
        val actual = dealer.compareWith(player)

        // assert
        assertThat(actual).isEqualTo(expect)
    }

    @Test
    fun `딜러는 21이하이고 플레이어가 21을 넘으면 딜러가 승리한다`() {
        // arrange
        val dealer = Dealer(
            cardDeck(Card.diamond(Number.TEN), Card.heart(Number.ACE))
        )
        val player = Player(
            "player",
            cardDeck(Card.spade(Number.TEN), Card.clover(Number.JACK), Card.heart(Number.TWO))
        ).apply { obtain() }

        // act
        val actual = dealer.compareWith(player)

        // assert
        assertThat(actual).isEqualTo(CompareResult.DEALER_WIN)
    }

    private fun cardDeck(vararg cards: Card): CardDeck {
        val iterator = cards.iterator()
        return CardDeck { iterator.next() }
    }
}
