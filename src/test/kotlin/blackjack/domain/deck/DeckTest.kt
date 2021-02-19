package blackjack.domain.deck

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class DeckTest {

    @Test
    fun `52장의_덱을_만든다`() {
        val deck = Deck.createDeck()

        assertThat(deck.cards.size).isEqualTo(52)
    }

    @Test
    fun `뽑을_때마다_한장씩_줄어든다`() {
        val deck = Deck.createDeck()
        deck.draw()
        assertThat(deck.cards.size).isEqualTo(51)
    }

    @Test
    fun `뽑을게_없다면_에러`() {
        val deck = Deck.createDeck()
        for (i in 1..52) {
            deck.draw()
        }
        assertThrows<IllegalArgumentException> { deck.draw() }
    }
}
