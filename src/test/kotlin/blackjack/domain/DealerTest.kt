package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DealerTest {

    @Test
    fun `딜러는 발급 받은 첫 카드만 공개한다`() {
        val dealer = Dealer(cardDeck(Card.diamond(Number.TEN), Card.heart(Number.FIVE)))

        val actual = dealer.openedCards

        assertThat(actual).isEqualTo(Card.diamond(Number.TEN))
    }

    private fun cardDeck(vararg cards: Card): CardDeck {
        val iterator = cards.iterator()
        return CardDeck { iterator.next() }
    }
}
