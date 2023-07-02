package blackjack.domain.card

import blackjack.domain.GameResultState
import kotlin.math.abs
import kotlin.math.min

class Cards(private val cards: MutableSet<Card>) : MutableSet<Card> by cards {
    fun addCard(card: Card) {
        cards.add(card)
    }

    fun getCardsSortByScore(): List<Card> {
        return cards.sortedBy { it.cardNumber.score }
    }

    fun match(score: Int): GameResultState {
        var gameResult = GameResultState.DRAW
        val dealerGap = absScore(score)
        val playerGap = absScore(getCardScore())
        if (dealerGap < playerGap) {
            gameResult = GameResultState.LOSE
        }
        if (dealerGap > playerGap) {
            gameResult = GameResultState.WIN
        }
        return gameResult
    }

    fun getCardScore(): Int {
        val minScore = getBaseTotalScore()
        val hasAce = cards.any {
            it.cardNumber == CardNumber.CARD_ACE
        }
        val maxScore = minScore + if (hasAce) MAX_PLUS_SCORE else MIN_PLUS_SCORE
        if (maxScore > WIN_SCORE) {
            return minScore
        }
        return if (min(absScore(minScore), absScore(maxScore)) == absScore(minScore)) {
            minScore
        } else {
            maxScore
        }
    }

    private fun absScore(score: Int) = abs(WIN_SCORE - score)

    private fun getBaseTotalScore(): Int {
        return cards.sumOf {
            it.cardNumber.score
        }
    }

    fun hasMoreCard(count: Int): Boolean {
        return cards.size > count
    }

    fun hasLessScore(score: Int): Boolean {
        return getCardScore() < score
    }

    fun hasMoreScore(score: Int): Boolean {
        return getCardScore() > score
    }

    companion object {

        const val WIN_SCORE = 21
        const val MAX_PLUS_SCORE = 10
        const val MIN_PLUS_SCORE = 0
    }
}
