package blackjack.domain

import blackjack.domain.Game.Companion.MAXIMUM_GAME_SCORE

class Cards(cards: Set<Card>) {
    private val cards = cards.toMutableSet()

    fun add(newCard: Card): Set<Card> {
        cards.add(newCard)
        return cards.toSet()
    }

    fun size() = cards.size

    fun isMoreThanMaxScore(cards: Cards) = cards.sumOfScores() >= MAXIMUM_GAME_SCORE

    fun sumOfScores(): Int {
        var sum = cards.map { it.score() }.sumBy { it }
        return CardScore.sumWithAce(sum, hasAce())
    }

    private fun hasAce() = cards.any { it.isAce() }

    override fun toString(): String {
        return cards.joinToString { it.toString() }
    }
}
