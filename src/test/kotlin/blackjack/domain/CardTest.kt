package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CardTest {
    @Test
    fun `card test`() {
        assertThat(Card(Suit.CLUBS, Denomination.ACE)).isEqualTo(Card(Suit.CLUBS, Denomination.ACE))
    }
}
