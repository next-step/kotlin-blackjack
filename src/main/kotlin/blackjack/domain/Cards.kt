package blackjack.domain

import blackjack.domain.Rank.Companion.ACE

data class Cards(private val values: List<Card>) {
    fun isFullScore(): Boolean {
        return calculateScore() >= BLACKJACK_SCORE_LIMIT
    }

    fun add(card: Card): Cards {
        return Cards(values + card)
    }

    private fun calculateScore(): Int {
        val totalScore = values.sumOf { it.rank.score }
        var aceCount = values.count { it.rank == ACE }

        var adjustedScore = totalScore
        while (adjustedScore > BLACKJACK_SCORE_LIMIT && aceCount > 0) {
            adjustedScore -= ACE_SCORE_DIFFERENCE
            aceCount--
        }
        return adjustedScore
    }

    companion object {
        private const val BLACKJACK_SCORE_LIMIT = 21
        private const val ACE_SCORE_DIFFERENCE = 10
    }
}
