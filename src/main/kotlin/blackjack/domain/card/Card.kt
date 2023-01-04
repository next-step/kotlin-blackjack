package blackjack.domain.card

class Card(val suit: Suit, val number: Number) {

    private fun isAce(): Boolean = number == Number.ACE

    fun getPoint(totalPoint: Int = 0): Int {
        if (isAce()) {
            return if (totalPoint > ACE_NUMBER) number.value else number.orValue
        }

        return number.value
    }

    companion object {
        const val BLACK_JACk_NUMBER = 21
        const val ACE_NUMBER = 11
    }
}
