package blackjack

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow

internal class DeckTest {
    @Test
    internal fun `덱은 카드리스트로 생성된다`() {
        // given
        val cards = listOf(Card(Suit.HEARTS, Symbol.ACE))

        assertDoesNotThrow { Deck(cards) }
    }
}
