package domain.algorithm

import domain.Card
import domain.CardNumber
import kotlin.math.absoluteValue

sealed interface ScoreOptimizationAlgorithm {
    fun optimizeScore(cards: List<Card>, targetScore: Int): Int
}

object DefaultScoreOptimizationAlgorithm : ScoreOptimizationAlgorithm {
    override fun optimizeScore(cards: List<Card>, targetScore: Int): Int {
        val aceCardList = cards.filter { it.number == CardNumber.ACE }.toList()

        var baseScore = cards.sumOf { it.number.primaryScore }
        aceCardList.forEach {
            val absPrimaryDiff = (targetScore - (baseScore)).absoluteValue
            val absSecondaryDiff = (targetScore - (baseScore - it.number.primaryScore + it.number.secondaryScore)).absoluteValue
            if (absPrimaryDiff > absSecondaryDiff) {
                baseScore -= it.number.primaryScore
                baseScore += it.number.secondaryScore
            }
        }
        return baseScore
    }
}
