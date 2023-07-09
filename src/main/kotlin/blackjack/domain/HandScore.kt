package blackjack.domain

import blackjack.domain.card.CardScore

@JvmInline
value class HandScore(val value: Int) {
    fun isExceedsMax() = value > MAX_VALUE

    fun isMax() = value == MAX_VALUE

    fun plus(score: CardScore): HandScore {
        val primaryHandScoreAdded = HandScore(score.primary + value)
        val secondaryHandScoreAdded = HandScore(score.secondary + value)
        return primaryHandScoreAdded.takeIf { it.isExceedsMax().not() } ?: secondaryHandScoreAdded
    }

    fun plus(scores: List<CardScore>): HandScore {
        return scores
            .sortedByDescending { it.isPrimaryEqualToSecondary() }
            .fold(this) { acc, score -> acc.plus(score) }
    }

    companion object {
        const val MAX_VALUE = 21
        val ZERO = HandScore(0)
    }
}
