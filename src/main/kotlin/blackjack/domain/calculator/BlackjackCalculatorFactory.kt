package blackjack.domain.calculator

import blackjack.domain.deck.Cards

object BlackjackCalculatorFactory {
    private val CALCULATORS = listOf(AceCalculator, NormalCalculator)

    fun getCalculator(cards: Cards): BlackjackCalculator {
        return CALCULATORS.first { it.isAssignablefrom(cards) }
    }
}
