package blackjack.domain

data class Card(private val cardNumber: CardNumber, private val suit: Suit) {

    fun getScore(isAceEleven: Boolean = false): Int {
        return cardNumber.getScore(isAceEleven)
    }

    override fun toString(): String {
        return "${cardNumber.symbol}${suit.rawValue}"
    }
}
