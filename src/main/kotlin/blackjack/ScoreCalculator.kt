package blackjack

import blackjack.card.AceCard
import blackjack.card.BlackJackCard
import blackjack.card.NormalCard
import blackjack.card.PictureCard
import kotlin.math.abs

class ScoreCalculator {
    fun calcScore(card: List<BlackJackCard>): Int {
        return card.filterIsInstance<AceCard>().fold(calcScoreToNormalAndPictureCard(card)) { acc, _ ->
            calcAceScore(acc)
        }
    }

    private fun calcAceScore(score: Int): Int {
        var totalScore = score
        val plusMin = totalScore + MIN_ACE_SCORE
        val plusMax = totalScore + MAX_ACE_SCORE

        totalScore += when {
            (abs(plusMin - BEST_SCORE) < abs(plusMax - BEST_SCORE)) -> MIN_ACE_SCORE
            (abs(plusMin - BEST_SCORE) > abs(plusMax - BEST_SCORE)) -> MAX_ACE_SCORE
            else -> MIN_ACE_SCORE
        }

        return totalScore
    }

    private fun calcScoreToNormalAndPictureCard(card: List<BlackJackCard>) = card.sumOf {
        when (it) {
            is NormalCard -> it.number
            is PictureCard -> PICTURE_CARD_SCORE
            else -> 0
        }
    }

    companion object {
        private const val PICTURE_CARD_SCORE: Int = 10
        private const val BEST_SCORE: Int = 21
        private const val MIN_ACE_SCORE: Int = 1
        private const val MAX_ACE_SCORE: Int = 11
    }
}
