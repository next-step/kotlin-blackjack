package blackjack.domain.deck

import blackjack.domain.calculator.BlackjackCalculator

class Cards(val cards: List<Card>) {

    fun calculateScore(blackjackCalculator: BlackjackCalculator): Int {
        return blackjackCalculator.calculate(cards)
    }

    fun existAce(): Boolean {
        return cards.any { it.isAce() }
    }
}
