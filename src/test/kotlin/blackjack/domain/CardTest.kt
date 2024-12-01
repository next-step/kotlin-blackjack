package blackjack.domain

import blackjack.domain.Card.Companion.MAX_NUMBER
import blackjack.domain.Card.Companion.MIN_NUMBER
import blackjack.domain.Card.Companion.SpecialNumber
import io.kotest.matchers.collections.shouldBeIn
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class CardTest {
    @Test
    fun `checkAllCardInstance - should be valid card`() {
        val validNumbers = (MIN_NUMBER..MAX_NUMBER).map { it.toString() } + SpecialNumber.entries.map { it.name }
        val validSymbols = Card.Companion.SYMBOL.entries

        Card.cards.forEach { card ->
            card.number shouldBeIn validNumbers
            card.symbol shouldBeIn validSymbols
        }
    }

    @Test
    fun `check invalid card - throw exception`() {
        assertThrows<IllegalArgumentException> { Card.createCard("12", "스페이드") }
    }
}
