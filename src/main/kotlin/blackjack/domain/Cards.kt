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
        var score = cards.map { it.score() }.sumBy { it }
        while (score <= EVELVEN_ACE_AVAILABLE_SCORE && hasAce()) {
            score += ADDED_SCORE_WHEN_USING_ELEVEN_ACE
        }
        return score
    }

    private fun hasAce() = cards.any { it.score() == 1 }

    override fun toString(): String {
        return cards.joinToString { it.toString() }
    }

    companion object {
        const val EVELVEN_ACE_AVAILABLE_SCORE = 11
        const val ADDED_SCORE_WHEN_USING_ELEVEN_ACE = 10
    }
}
