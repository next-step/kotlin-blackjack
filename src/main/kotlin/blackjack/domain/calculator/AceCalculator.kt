package blackjack.domain.calculator

import blackjack.domain.deck.Card
import blackjack.domain.deck.Cards

object AceCalculator : BlackjackCalculator {
    private const val MAXIMUM_POINT = 21
    private const val LOWER_ACE_POINT = 1

    override fun isAssignablefrom(cards: Cards): Boolean {
        return cards.existAce()
    }

    override fun calculate(cards: List<Card>): Int {
        val aceCards = cards.filter { it.isAce() }
        val notAceCards = cards.filter { !it.isAce() }
        var resultWithOutAce = notAceCards.sumBy { it.denomination.point }

        for (aceCard in aceCards) {
            resultWithOutAce = calculateAce(aceCard, resultWithOutAce)
        }

        return resultWithOutAce
    }

    private fun calculateAce(aceCard: Card, resultWithOutAce: Int): Int {
        val higherPointAddResult = aceCard.denomination.point.plus(resultWithOutAce)
        if (higherPointAddResult > MAXIMUM_POINT) {
            return resultWithOutAce.plus(LOWER_ACE_POINT)
        }
        return higherPointAddResult
    }
}
