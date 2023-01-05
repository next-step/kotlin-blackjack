package model

class CardNumberCalculator {
    fun isGetExtraCard(cards: List<Card>): Boolean {
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

    fun decideAceCardNumber(totalExceptAce: Int): Int {
        val ace = when (totalExceptAce) {
            in RANGE_FOR_ACE_11 -> 11
            in RANGE_FOR_ACE_1 -> 1
            else -> 1
        }
        return ace
    }

    companion object {
        private const val BLACK_JACK = 21
        private val RANGE_FOR_ACE_11 = 2..10
        private val RANGE_FOR_ACE_1 = 11..20
    }
}
