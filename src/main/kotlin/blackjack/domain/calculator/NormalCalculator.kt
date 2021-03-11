package blackjack.domain.calculator

import blackjack.domain.deck.Card
import blackjack.domain.deck.Cards

object NormalCalculator : BlackjackCalculator {
    override fun isAssignablefrom(cards: Cards): Boolean {
        return !cards.existAce()
    }

    override fun calculate(cards: List<Card>): Int {
        return cards.sumBy { it.denomination.point }
    }
}
