package model

class CardNumberCalculator {
    fun isUnderTwentyOne(cards: List<Card>): Boolean {
        var total = 0
        cards.forEach {
            total += it.cardNumber.number
        }
        return total < BLACK_JACK
    }

    companion object {
        private const val BLACK_JACK = 21
    }
}
