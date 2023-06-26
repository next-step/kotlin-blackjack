package blackjack.domain.score

import blackjack.domain.card.Card
import blackjack.domain.card.CardScoreType
import blackjack.domain.card.sum
import blackjack.domain.card.sumLargeValue
import blackjack.domain.card.sumSmallValue

object CardScoreCalculator {

    fun calculateScore(cards: List<Card>): Score {
        if (cards.isEmpty()) {
            return Score(0)
        }

        val scores = cards.classifyByScore()
            .calculateSumCases()
            .sortedDescending()
            .map { sum -> Score(sum) }

        return scores.firstOrNull { it.isAlive } ?: scores.last()
    }

    private fun List<Card>.classifyByScore(): CardClassifiedResult {
        val flexibleScores = mutableListOf<CardScoreType.Flexible>()
        val fixedScores = mutableListOf<CardScoreType.Fixed>()

        forEach { card ->
            when (val score = card.denomination.score) {
                is CardScoreType.Flexible -> flexibleScores.add(score)
                is CardScoreType.Fixed -> fixedScores.add(score)
            }
        }

        return CardClassifiedResult(flexibleScores, fixedScores)
    }

    private data class CardClassifiedResult(
        val flexibleScores: List<CardScoreType.Flexible>,
        val fixedScores: List<CardScoreType.Fixed>,
    ) {

        fun calculateSumCases(): List<Int> {
            val fixedScoreSum = fixedScores.sum()
            if (flexibleScores.isEmpty()) {
                return listOf(fixedScoreSum)
            }

            val largeScoreStartPickSize = 0
            val largeScoreEndPickSize = 1
            return (largeScoreStartPickSize..largeScoreEndPickSize).map { largeScorePickSize ->
                val smallScorePickSize = flexibleScores.size - largeScorePickSize
                val largeScoreSum = flexibleScores.take(largeScorePickSize).sumLargeValue()
                val smallScoreSum = flexibleScores.take(smallScorePickSize).sumSmallValue()
                fixedScoreSum + largeScoreSum + smallScoreSum
            }
        }
    }
}
