package blackjack.card

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows

class CardsTest {
    @Test
    fun `카드 수 이상으로 카드를 뽑으면 에러가 발생한다`() {
        val cards = Cards(Card(Suit.SPADE, CardSymbol.SEVEN))
        assertDoesNotThrow { cards.drawCard() }
        assertThrows<NoSuchElementException> { cards.drawCard() }
    }
}
