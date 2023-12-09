package blackjack.game

import blackjack.card.AceCard
import blackjack.card.BlackJackCard
import blackjack.card.NormalCard
import blackjack.card.PictureCard
import kotlin.math.abs

class ScoreCalculator {
    fun calculateGameScore(card: List<BlackJackCard>): Int {
        return card.filterIsInstance<AceCard>().fold(calcScoreToNormalAndPictureCard(card)) { acc, _ ->
            calculateAceScore(acc)
        }
    }

    private fun calculateAceScore(score: Int): Int {
        var totalScore = score
        val plusMinAceScore = totalScore + MIN_ACE_SCORE
        val plusMaxAcreScore = totalScore + MAX_ACE_SCORE

        totalScore += when {
            (isMinAceScoreCloser(plusMaxAcreScore, plusMinAceScore)) -> MIN_ACE_SCORE
            else -> MAX_ACE_SCORE
        }

        return totalScore
    }

    private fun isMinAceScoreCloser(plusMaxAcreScore: Int, plusMinAceScore: Int): Boolean {
        return abs(plusMinAceScore - BEST_SCORE) < abs(plusMaxAcreScore - BEST_SCORE)
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
