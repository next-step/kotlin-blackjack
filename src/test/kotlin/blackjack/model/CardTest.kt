package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CardTest {
    @Test
    internal fun `카드는 슈트와 끗수로 구성된다`() {
        val suit = Suit.SPADE
        val denomination = Denomination.ACE
        val card = Card(suit, denomination)
        assertThat(card.suit).isSameAs(suit)
        assertThat(card.denomination).isSameAs(denomination)
    }
}
