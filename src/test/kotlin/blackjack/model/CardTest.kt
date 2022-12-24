package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CardTest {
    @Test
    internal fun `카드는 슈트와 끗수로 구성된다`() {
        // given
        val suit = Suit.SPADE
        val denomination = Denomination.ACE

        // when
        val card = Card(suit, denomination)

        // then
        assertThat(card.suit).isSameAs(suit)
        assertThat(card.denomination).isSameAs(denomination)
    }
}
