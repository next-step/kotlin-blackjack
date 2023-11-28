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

    private fun cardDeck(vararg cards: Card): CardDeck {
        val iterator = cards.iterator()
        return CardDeck { iterator.next() }
    }
}
