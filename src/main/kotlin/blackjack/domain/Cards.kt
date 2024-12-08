package blackjack.domain

import blackjack.domain.MatchResult.DRAW
import blackjack.domain.MatchResult.LOSS
import blackjack.domain.MatchResult.WIN

data class Cards(val values: List<Card>) {
    val score: Int
        get() = calculateScore()

    val isBust: Boolean
        get() = calculateScore() > BLACKJACK_SCORE_LIMIT

    fun scoreLowerThan(limit: Int): Boolean {
        return score < limit
    }

    fun compareScore(other: Cards): MatchResult {
        return when {
            score > other.score -> WIN
            score < other.score -> LOSS
            else -> DRAW
        }
    }

    fun add(card: Card): Cards {
        return Cards(values + card)
    }

    private fun calculateScore(): Int {
        val totalScore = values.sumOf { it.score }
        var aceCount = values.count { it.isAce }

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
