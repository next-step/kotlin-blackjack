package blackjack.domain

import kotlin.math.abs

class Score(
    private val cards: Cards
) {
    val value: Int = toScore()

    fun isBlackjack(): Boolean {
        return cards.size == 2 && value == BLACKJACK
    }

    fun isBust(): Boolean {
        return value > BLACKJACK
    }

    fun gapFromBlackjack(): Int {
        return abs(value - BLACKJACK)
    }

    private fun toScore(): Int {
        val denominations = cards.values.map { it.denomination }
        var score = denominations.sumOf { it.score }
        var numOfAce = denominations.count { it == Denomination.ACE }
        while (numOfAce > 0) {
            if (score > BLACKJACK) {
                score -= (Denomination.ACE.score - Denomination.ACE.aceScore)
            }
            numOfAce -= 1
        }
        return score
    }

    companion object {
        const val BLACKJACK = 21
        const val DEALER_STAND_THRESHOLD = 17
    }
}
