package blackjack

import blackjack.card.AceCard
import blackjack.card.BlackJackCard
import blackjack.card.NormalCard
import blackjack.card.PictureCard
import kotlin.math.abs

class ScoreCalculator {
    fun calcScore(card: List<BlackJackCard>): Int {
        var score = card.sumOf {
            when (it) {
                is NormalCard -> it.number
                is PictureCard -> PICTURE_CARD_SCORE
                else -> 0
            }
        }

        card.filter { it is AceCard }
            .forEach {
                val plusMin = score + MIN_ACE_SCORE
                val plusMax = score + MAX_ACE_SCORE
                println(abs(plusMin - BEST_SCORE))
                println(abs(plusMax - BEST_SCORE))

                score += when {
                    (abs(plusMin - BEST_SCORE) < abs(plusMax - BEST_SCORE)) -> MIN_ACE_SCORE
                    (abs(plusMin - BEST_SCORE) > abs(plusMax - BEST_SCORE)) -> MAX_ACE_SCORE
                    else -> MIN_ACE_SCORE
                }
            }

        return score
    }

    companion object {
        private const val PICTURE_CARD_SCORE: Int = 10
        private const val BEST_SCORE: Int = 21
        private const val MIN_ACE_SCORE: Int = 1
        private const val MAX_ACE_SCORE: Int = 11
    }
}
