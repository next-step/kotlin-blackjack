package blackjack.domain

import blackjack.domain.BlackJackRules.BLACKJACK_SCORE_LIMIT

data class Cards(val values: List<Card>) {
    fun calculateScore(): Int {
        val totalScore = values.sumOf { it.score }
        var aceCount = values.count { it.isAce() }

        var adjustedScore = totalScore
        while (adjustedScore > BLACKJACK_SCORE_LIMIT && aceCount > 0) {
            adjustedScore -= ACE_SCORE_DIFFERENCE
            aceCount--
        }
        return adjustedScore
    }

    fun add(card: Card): Cards {
        return Cards(values + card)
    }

    companion object {
        private const val ACE_SCORE_DIFFERENCE = 10
    }
}
