package blackjack.domain.deck

import blackjack.domain.calculator.BlackjackCalculator

class Cards(cards: List<Card>) {
    private var _cards = cards.toMutableList()
    val cards: List<Card>
        get() {
            return _cards.toList()
        }

    fun calculateScore(blackjackCalculator: BlackjackCalculator): Int {
        return blackjackCalculator.calculate(cards)
    }

    fun existAce(): Boolean {
        return cards.any { it.isAce() }
    }

    fun add(card: Card) {
        _cards.add(card)
    }
}
