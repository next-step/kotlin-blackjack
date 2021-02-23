package blackjack.domain.player

import blackjack.domain.calculator.BlackjackCalculatorFactory
import blackjack.domain.deck.Cards
import blackjack.domain.deck.Deck

abstract class Status(val cards: Cards) {
    fun calculate(): Int {
        val calculator = BlackjackCalculatorFactory.getCalculator(cards)
        return cards.calculateScore(calculator)
    }

    abstract fun draw(deck: Deck): Status

    abstract fun stay(): Status

    abstract fun isFinished(): Boolean
}
