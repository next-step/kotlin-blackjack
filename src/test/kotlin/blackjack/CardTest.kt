package blackjack

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class CardTest {

    @Test
    fun `카드는 끗수와 모양을 가진다`() {
        val card = Card(Denomination.ACE, Suit.SPADE)
        assertAll(
            { assertThat(card.denomination).isEqualTo(Denomination.ACE) },
            { assertThat(card.suit).isEqualTo(Suit.SPADE) }
        )
    }
}
