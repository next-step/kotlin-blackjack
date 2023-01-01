package domain.algorithm

import domain.card.Card
import domain.card.CardNumber

sealed interface ScoreOptimizationAlgorithm {
    fun optimizeScore(cards: List<Card>, targetScore: Int): Int
}

object DefaultScoreOptimizationAlgorithm : ScoreOptimizationAlgorithm {
    override fun optimizeScore(cards: List<Card>, targetScore: Int): Int {
        val aceCardList = cards.filter { it.number == CardNumber.ACE }.toList()

        var baseScore = cards.sumOf { it.number.primaryScore }
        aceCardList.forEach {
            val primaryDiff = targetScore - baseScore
            val secondaryDiff = targetScore - (baseScore - it.number.primaryScore + it.number.secondaryScore)
            if (secondaryDiff in 0 until primaryDiff) {
                baseScore -= it.number.primaryScore
                baseScore += it.number.secondaryScore
            }
        }
        return baseScore
    }
}
