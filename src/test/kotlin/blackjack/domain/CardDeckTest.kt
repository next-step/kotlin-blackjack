package blackjack.domain

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class CardDeckTest {
    @Test
    @DisplayName("{given} 초기 Deck 상태 {when} draw() {then} 카드 리스트 사이즈 1 감소")
    fun `should decrease card list size by 1 when draw() is called on an initial deck`() {
        // Given
        val deck = CardDeck()
        val initialSize = Rank.entries.size * Suit.entries.size

        // When
        val drawnCard = deck.draw()
        val currentSize = initialSize - 1

        // Then
        assertNotNull(drawnCard)
        assertEquals(currentSize, deck.cards.size)
    }

    @Test
    @DisplayName("{given} Empty Deck {when} draw() {then} IllegalStateException 발생")
    fun `should throw IllegalStateException when draw() is called on an empty deck`() {
        // Given
        val deck =
            CardDeck(
                ranks = emptyList(),
                suits = emptyList(),
            )

        // When & Then
        val exception =
            assertThrows(IllegalStateException::class.java) {
                deck.draw()
            }
        assertEquals("No more cards", exception.message)
    }
}
