package blackjack.domain

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test

class CardDeckTest {
    @Test
    fun `{given} 초기 Deck 상태 {when} draw() {then} 카드 리스트 사이즈 1 감소`() {
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
    fun `{given} Empty deck {when&then} IllegalStateException 발생`() {
        // Given
        val deck = CardDeck()

        // 모든 카드를 다 뽑는다
        while (deck.cards.isNotEmpty()) {
            deck.draw()
        }

        // When & Then
        val exception =
            assertThrows(IllegalStateException::class.java) {
                deck.draw()
            }
        assertEquals("No more cards", exception.message)
    }
}
