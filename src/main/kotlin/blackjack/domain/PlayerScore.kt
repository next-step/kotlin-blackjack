package blackjack.domain

import blackjack.domain.card.CardScore

@JvmInline
value class PlayerScore(val value: Int) {
    fun add(score: CardScore): PlayerScore {
        val primaryScoreAdded = PlayerScore(score.primary + value)
        val secondaryScoreAdded = PlayerScore(score.secondary + value)
        return primaryScoreAdded.takeIf { it.isMaxExceeded().not() } ?: secondaryScoreAdded
    }

    private fun isMaxExceeded(): Boolean = value > MAX

    fun add(scores: List<CardScore>): PlayerScore {
        return scores
            .sortedByDescending { it.isPrimaryEqualToSecondary() }
            .fold(this) { acc, score -> acc.add(score) }
    }

    fun isMaxOrExceeded(): Boolean = value >= MAX

    companion object {
        const val MAX = 21
        val ZERO = PlayerScore(0)
    }
}
