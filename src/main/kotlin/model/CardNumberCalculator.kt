package model

import kotlin.math.abs

class CardNumberCalculator {
    fun isUnderTwentyOne(cards: List<Card>): Boolean {
        return totalNumber(cards) < BLACK_JACK
    }

    fun totalNumber(cards: List<Card>): Int {
        var total = 0
        var isAce = false
        cards.forEach {
            if (it.cardNumber != CardNumber.ACE) {
                total += it.cardNumber.number
            } else {
                isAce = true
            }
        }
        if (isAce) total += decideAceCardNumber(total)
        return total
    }

    fun decideAceCardNumber(semiTotal: Int): Int {
        val ace = if (abs(BLACK_JACK - (semiTotal + 1)) <= abs(BLACK_JACK - (semiTotal + 11))) {
            1
        } else {
            11
        }
        return ace
    }

    companion object {
        private const val BLACK_JACK = 21
    }
}
