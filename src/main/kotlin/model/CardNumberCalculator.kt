package model

object CardNumberCalculator {
    private val RANGE_FOR_ACE_11 = 0..10

    fun sumCardNumbers(cards: List<Card>): Int {
        var total = 0
        cards.forEach {
            total += if (it.cardNumber.isAce()) {
                decideAceCardNumber(total)
            } else {
                it.cardNumber.number
            }
        }
        return total
    }

    fun decideAceCardNumber(totalExceptAce: Int): Int {
        val ace = when (totalExceptAce) {
            in RANGE_FOR_ACE_11 -> 11
            else -> 1
        }
        return ace
    }
}
