package blackjack.domain

data class Card(private val cardNumber: CardNumber, private val suit: Suit) {

    fun getScore(isAceEleven: Boolean = false): Int {
        return when (val number = cardNumber) {
            CardNumber.Ace -> if (isAceEleven) 11 else 1
            CardNumber.King, CardNumber.Queen, CardNumber.Jack -> 10
            else -> number.rawValue.toInt()
        }
    }

    override fun toString(): String {
        return "${cardNumber.rawValue}${suit.rawValue}"
    }
}
