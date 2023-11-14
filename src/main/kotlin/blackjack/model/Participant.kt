package blackjack.model

import blackjack.controller.BlackJackGame.Companion.BEST_SCORE
import kotlin.math.max
import kotlin.math.min

data class Participant(
    val name: String,
    val cards: MutableList<Card> = mutableListOf(),
) {
    fun isPossibleToTakeMoreCard(): Boolean {
        return checkCurrentScore() < BEST_SCORE
    }

    private fun checkCurrentScore(): Int {
        return cards.sumOf { it.info.value1 }
    }

    fun takeBestScore(): Int {
        val scoreWithoutAce = cards.filter { it.info != CardInfo.Ace }.sumOf { it.info.value1 }
        val countOfAce = cards.count { it.info == CardInfo.Ace }
        var bestScore = scoreWithoutAce + countOfAce * CardInfo.Ace.value1
        (0 until countOfAce).forEach { countOfScoreOneAce ->
            val scoreOfAces =
                countOfScoreOneAce * CardInfo.Ace.value1 + (countOfAce - countOfScoreOneAce) * CardInfo.Ace.value2
            val totalScore = scoreWithoutAce + scoreOfAces
            bestScore = if (totalScore <= BEST_SCORE) {
                max(bestScore, totalScore)
            } else {
                min(bestScore, totalScore)
            }
        }
        return bestScore
    }
}
