package blackJack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DeckTest {
    @Test
    fun make_deck() {
        val deck = Deck()

        assertThat(deck.cards.size).isEqualTo(52)
    }

    @Test
    fun shuffle_deck() {
        val deck = Deck()
        val deck2 = Deck()

        deck2.shuffle()

        assertThat(deck.cards).isNotEqualTo(deck2.cards)
    }

    @Test
    fun get_card() {
        val deck = Deck()

        deck.getCard()

        assertThat(deck.cards.size).isEqualTo(51)
    }
}

