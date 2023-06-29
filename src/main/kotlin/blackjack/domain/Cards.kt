package blackjack.domain

import kotlin.math.abs
import kotlin.math.min

class Cards(private val cards: MutableSet<Card>) : MutableSet<Card> by cards {
    fun addCard(card: Card) {
        cards.add(card)
    }

    fun getTotalScore(): Int {
        return cards.sumOf {
            it.cardNumber.score
        }
    }

    fun getCardsSortByScore(): List<Card> {
        return cards.sortedBy { it.cardNumber.score }
    }

    fun getCardScore(): Int {
        val minScore = getTotalScore()
        val hasAce = cards.any {
            it.cardNumber == CardNumber.CARD_ACE
        }
        val maxScore = minScore + if (hasAce) MAX_PLUS_SCORE else MIN_PLUS_SCORE
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
        const val MAX_PLUS_SCORE = 10
        const val MIN_PLUS_SCORE = 0
    }
}
