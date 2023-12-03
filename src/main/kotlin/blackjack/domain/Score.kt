package blackjack.domain

import kotlin.math.abs

class Score(
    private val cards: Cards
) {
    companion object {
        const val BLACKJACK = 21
        const val DEALER_STAND_THRESHOLD = 16
    }

    fun isBlackjack(): Boolean {
        return cards.size == 2 && calc() == BLACKJACK
    }

    fun isBust(): Boolean {
        return calc() > BLACKJACK
    }

    fun gapFromBlackjack(): Int {
        return abs(calc() - BLACKJACK)
    }

    fun calc(): Int {
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
}

fun Cards.toScore(): Int {
    return Score(this).calc()
}
