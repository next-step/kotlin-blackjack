package domain

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class DeckTest {

    @Test
    @DisplayName("덱 초기화 및 섞기")
    fun `덱은 모든 카드로 초기화되어 섞여야 함`() {
        val deck = Deck()
        val drawnCards = mutableSetOf<Card>()

        repeat(52) {
            deck.drawCard()?.let { drawnCards.add(it) }
        }

        assertEquals(52, drawnCards.size)
    }

    @Test
    @DisplayName("카드 추출")
    fun `카드를 추출할 때마다 덱에서 제거되어야 함`() {
        val deck = Deck()
        val firstCard = deck.drawCard()
        val secondCard = deck.drawCard()

        assertNotNull(firstCard)
        assertNotNull(secondCard)
        assertNotEquals(firstCard, secondCard)
    }

    @Test
    @DisplayName("덱이 비어있을 때 카드 추출")
    fun `덱이 비어있으면 null을 반환해야 함`() {
        val deck = Deck()
        repeat(52) { deck.drawCard() }

        assertNull(deck.drawCard())
    }
}
