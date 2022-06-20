package blackjack.card

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class DeckTest {
    @Test
    fun `덱 구성 카드는 52개다`() {
        val testCards = PlayCards(Card(Suit.SPADE, CardSymbol.SEVEN))
        assertThrows<IllegalArgumentException> { (Deck(testCards)) }
    }
}
