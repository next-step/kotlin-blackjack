package domain

import enum.Rank

class Cards {
    private val cards = mutableListOf<Card>()

    fun add(card: Card) {
        cards.add(card)
    }

    fun calculateScore(): Int {
        var score = cards.sumOf { it.rank.value }
        val aceCount = cards.count { it.rank == Rank.ACE }

        repeat(aceCount) {
            if (score > BlackjackRules.MAXIMUM_SCORE) {
                score -= BlackjackRules.ACE_HIGH_TO_LOW_DIFFERENCE
            }
        }
        return score
    }

    fun showHand(): List<Card> = cards.toList()
}
