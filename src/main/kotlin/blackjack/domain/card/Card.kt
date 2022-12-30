package blackjack.domain.card

class Card(val suit: Suit, val number: Number) {

    private fun isAce(): Boolean = number == Number.ACE

    fun getPoint(totalPoint: Int = 0): Int {
        if (isAce()) {
            val maxValue = totalPoint + number.orValue
            return if (maxValue < BLACK_JACk_NUMBER) number.orValue else number.value
        }

        return number.value
    }

    companion object {
        const val BLACK_JACk_NUMBER = 21
    }
}
