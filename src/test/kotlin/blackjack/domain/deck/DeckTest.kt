package blackjack.domain.deck

import blackjack.domain.deck.Deck
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

internal class DeckTest {

    @Test
    fun `52장의_덱을_만든다`() {
        val deck = Deck.createDeck()

        Assertions.assertThat(deck.cards.size).isEqualTo(52)
    }

    @Test
    fun `뽑을_때마다_한장씩_줄어든다`() {
        val deck = Deck.createDeck()
        deck.draw()
        Assertions.assertThat(deck.cards.size).isEqualTo(51)
    }

    @Test
    fun `뽑을게_없다면_에러`() {
        val deck = Deck.createDeck()
        for (i in 1..52) {
            deck.draw()
        }
        org.junit.jupiter.api.assertThrows<IllegalArgumentException> { deck.draw() }
    }
}
