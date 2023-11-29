package card

class PlayingCard(private val suit: Suit, private val cardNumber: CardNumber) {
    fun getPoint(): Int {
        return cardNumber.point
    }

    fun getSuitName(): String {
        return suit.koName
    }

    fun getCardNumber(): CardNumber {
        return cardNumber
    }

    override fun toString(): String {
        return "${cardNumber.cardName}${suit.koName}"
    }
}
