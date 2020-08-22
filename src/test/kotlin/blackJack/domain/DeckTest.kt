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
    fun shuffle_deck() {
        val deck = Deck()
        val cards = listOf(Card(Shape.SPADE, Denomination.TEN), Card(Shape.SPADE, Denomination.TEN))

        deck.shuffle { cards }

        assertThat(deck.cards).hasSize(2)
    }

    @Test
    fun get_card() {
        val deck = Deck()

        val card = deck.getCard()

        assertThat(card).isEqualTo(Card(Shape.SPADE, Denomination.ACE))
        assertThat(deck.cards).hasSize(51)
    }
}
