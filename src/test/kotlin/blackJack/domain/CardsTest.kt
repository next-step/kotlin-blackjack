package blackJack.domain

import blackJack.error.ErrorMessage
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class CardsTest {

    @Test
    fun `cards 는 비어 있을 수 없다`() {
        val exception = assertThrows<IllegalArgumentException> {
            Cards(emptyList())
        }
        assertEquals(ErrorMessage.EMPTY_CARDS.message, exception.message)
    }

    @Test
    fun `card 는 중복될 수 없다`() {
        val card1 = Card(Suit.HEART, Rank.ACE)

        val exception = assertThrows<IllegalArgumentException> {
            Cards(listOf(card1, card1))
        }
        assertEquals(ErrorMessage.DUPLICATE_CARDS.message, exception.message)
    }
}
