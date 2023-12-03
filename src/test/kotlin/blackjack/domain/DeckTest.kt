package blackjack.domain

import blackjack.domain.Card.Companion.CARDS
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class DeckTest {
    private val cardsSize = CARDS.size

    @Test
    fun getCard() {
        // Given
        val deck = Deck(CARDS)

        // When
        deck.getCard()

        // Then
        assertThat(CARDS.size).isEqualTo(cardsSize - 1)
    }

    @ParameterizedTest
    @ValueSource(ints = [5, 10, 15])
    fun getCards(count: Int) {
        // Given
        val deck = Deck(CARDS)

        // When
        deck.getCards(count)

        // Then
        assertThat(CARDS.size).isEqualTo(cardsSize - count)
    }
}
