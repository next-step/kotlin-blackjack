package blackJack.domain

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class CardDeckTest {

    @Test
    fun `카드덱 생성`() {
        val cardDeck = CardDeck.createShuffledDeck()
        assertEquals(cardDeck.cards.size, 52)
    }

    @Test
    fun `카드는 중복된 값이 존재하지 않는다`() {
        val shuffledDeck = CardDeck.createShuffledDeck().cards
        val uniqueCards = shuffledDeck.toSet()
        assertEquals(shuffledDeck.size, uniqueCards.size)
        assertTrue { shuffledDeck.containsAll(uniqueCards) }
    }
}
