package blackjack

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DeckTest {

    @Test
    fun `Check initial Deck size`() {
        assertThat(Deck().size()).isEqualTo(48)
    }

    @Test
    fun `check reduce size after pop card from Deck`() {
        val sampleDeck = Deck()
        sampleDeck.popDeck()
        assertThat(sampleDeck.size()).isEqualTo(47)
    }

    @Test
    fun `return null, if Deck is empty `() {
        val sampleDeck = Deck()
        for (x in 0 until 48) {
            sampleDeck.popDeck()
        }
        assertThat(sampleDeck.popDeck()).isEqualTo(null)
        assertThat(sampleDeck.size()).isEqualTo(0)
    }
}
