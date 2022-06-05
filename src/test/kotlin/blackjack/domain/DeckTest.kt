package blackjack.domain

import blackjack.enums.Type
import blackjack.enums.Value
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class DeckTest {

    private val types = Type.values()
    private val values = Value.values()
    private val cards = (0..51).map { Card(types[it / 13], values[it % 13]) }

    @Test
    fun `the deck of cards can have fewer than 52 cards`() {
        assertThrows<IllegalArgumentException> { Deck(cards + cards) }
    }

    @ParameterizedTest
    @ValueSource(ints = [0, 3])
    fun `the user can draw number of cards only 1 or 2`(drawCounts: Int) {
        val deck = Deck(cards)

        assertThrows<IllegalArgumentException> { deck.draw(drawCounts) }
    }

    @Test
    fun `number of default drawn cards is 2`() {
        val deck = Deck(cards)
        val defaultDrawnCardCount = 2
        val expectedCards = listOf(Card(Type.SPADE, Value.ACE), Card(Type.SPADE, Value.TWO))

        val defaultDrawnCards = deck.draw()

        assertThat(defaultDrawnCards).hasSize(defaultDrawnCardCount)
        assertThat(defaultDrawnCards).isEqualTo(expectedCards)
    }
}
