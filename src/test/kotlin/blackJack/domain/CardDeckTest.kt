package blackJack.domain

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class CardDeckTest {

    @Test
    fun `카드덱 생성`() {
        val cardDeck = CardDeck.createShuffledDeck()
        assertEquals(cardDeck.cards.size, 52)
    }
}
