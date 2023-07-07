package blackjack.domain

import blackjack.domain.card.CardScore

@JvmInline
value class Score(val value: Int) {
    fun plus(score: CardScore): Score {
        val primaryScoreAdded = Score(score.primary + value)
        val secondaryScoreAdded = Score(score.secondary + value)
        return primaryScoreAdded.takeIf { it.isBust().not() } ?: secondaryScoreAdded
    }

    fun isBust() = value > MAX_VALUE

    fun plus(scores: List<CardScore>): Score {
        return scores
            .sortedByDescending { it.isPrimaryEqualToSecondary() }
            .fold(this) { acc, score -> acc.plus(score) }
    }

    companion object {
        const val MAX_VALUE = 21
        val ZERO = Score(0)
    }
}
