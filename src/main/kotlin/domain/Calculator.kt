package domain

import enum.Rank
import service.BlackjackRules

class Calculator {
    private val cards: MutableList<Card> = mutableListOf()

    fun receiveCard(card: Card) {
        cards.add(card)
    }

    fun calculateScore(): Int {
        var score = cards.sumOf { if (it.rank == Rank.ACE) BlackjackRules.ACE_HIGH_VALUE else it.rank.value }
        var aceCount = cards.count { it.rank == Rank.ACE }

        while (score > BlackjackRules.MAXIMUM_SCORE && aceCount > BlackjackRules.ACE_LOW_VALUE) {
            score -= 10
            aceCount--
        }

        return score
    }
}
