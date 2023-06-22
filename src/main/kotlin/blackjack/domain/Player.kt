package blackjack.domain

import kotlin.math.abs
import kotlin.math.min

class Player(val name: String) {

    val cards: MutableList<Card> = mutableListOf()

    fun addCard(card: Card) {
        cards.add(card)
    }

    fun getCardScore(): Int {
        val minScore = cards.sumOf {
            it.cardNumber.score
        }
        val aceCards = cards.filter {
            it.cardNumber == CardNumber.CARD_ACE
        }
        val maxScore = minScore + if (aceCards.isNotEmpty()) MAX_ACE_SCORE else MIN_ACE_SCORE
        if (maxScore > WIN_SCORE) {
            return minScore
        }
        return if (min(abs(WIN_SCORE - minScore), abs(WIN_SCORE - maxScore)) == abs(WIN_SCORE - minScore)) {
            minScore
        } else {
            maxScore
        }
    }

    companion object {
        const val WIN_SCORE = 21
        const val MAX_ACE_SCORE = 11
        const val MIN_ACE_SCORE = 0
    }
}
