package blackjack.player

import blackjack.card.Card

class PlayerCards {
    private val _cards = mutableListOf<Card>()
    private val cards: List<Card> get() = _cards.toList()
    fun add(card: Card) {
        _cards.add(card)
    }

    fun toList(): List<Card> {
        return cards
    }

    fun getScore(): Int {
        return BlackjackScoreCalculator.getScore(cards)
    }

    fun isBust(): Boolean {
        return BlackjackScoreCalculator.isBust(cards)
    }
}
