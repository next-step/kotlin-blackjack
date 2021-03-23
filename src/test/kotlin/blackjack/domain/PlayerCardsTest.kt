package blackjack.domain

import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

internal class PlayerCardsTest {

    @Test
    fun `카드가 2장이고 21점이면 blackJack이다`() {
        val cards = listOf(Card.of(CardSymbol.CLOVER, CardNumber.ACE), Card.of(CardSymbol.CLOVER, CardNumber.QUEEN))
        val playerCards = PlayerCards(cards)
        assertTrue(playerCards.isBlackJack())
    }

    @Test
    fun `카드가 2장이고 21초과시 burst이다`() {
        val cards = listOf(
            Card.of(CardSymbol.CLOVER, CardNumber.TEN),
            Card.of(CardSymbol.CLOVER, CardNumber.QUEEN),
            Card.of(CardSymbol.CLOVER, CardNumber.TWO)
        )
        val playerCards = PlayerCards(cards)
        assertTrue(playerCards.isBurst())
    }
}
