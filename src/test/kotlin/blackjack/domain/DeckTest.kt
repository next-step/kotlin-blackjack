package blackjack.domain

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows

class DeckTest {
    @Test
    fun `Deck은 52장의 카드를 뽑을 수 있다`() {
        val deck = Deck()

        assertDoesNotThrow {
            repeat(52) {
                deck.draw()
            }
        }
    }

    @Test
    fun `Deck은 52장 초과로 카드를 뽑을 수 없다`() {
        val deck = Deck()

        assertThrows<IllegalStateException> {
            repeat(53) {
                deck.draw()
            }
        }
    }
}