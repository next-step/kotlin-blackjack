package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CardsTest {
    @Test
    fun `cards in test`() {
        val cards = Cards()
        cards.add(Card(Suit.CLUBS, Denomination.ACE))
        assertThat(cards).contains(Card(Suit.CLUBS, Denomination.ACE))
    }
}
