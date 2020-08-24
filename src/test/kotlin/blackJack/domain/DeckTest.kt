package blackJack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DeckTest {
    @Test
    fun make_deck() {
        val deck = Deck()

        assertThat(deck.cards).hasSize(52)
    }

    @Test
    fun give_card() {
        val deck = Deck()

        val card = deck.getFirstCard()

        assertThat(card.toString()).isEqualTo("♠A")
        assertThat(deck.cards).hasSize(51)
    }
}
