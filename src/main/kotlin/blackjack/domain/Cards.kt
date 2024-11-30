package blackjack.domain

import blackjack.domain.Rank.Companion.ACE

data class Cards(val cards: List<Card>) {
    fun calculateScore(): Int {
        val totalScore = cards.sumOf { it.rank.score }
        var aceCount = cards.count { it.rank == ACE }

        var adjustedScore = totalScore
        while (adjustedScore > BLACKJACK_SCORE_LIMIT && aceCount > 0) {
            adjustedScore -= ACE_SCORE_DIFFERENCE
            aceCount--
        }
        return adjustedScore
    }

    fun isFullScore(): Boolean {
        return calculateScore() >= BLACKJACK_SCORE_LIMIT
    }

    companion object {
        private const val BLACKJACK_SCORE_LIMIT = 21
        private const val ACE_SCORE_DIFFERENCE = 10
    }
}
