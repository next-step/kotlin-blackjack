package card

import card.Suit.Companion.getName

class PlayingCard(private val suit: Suit, private val cardNumber: CardNumber) {
    fun getPoint(): Int {
        return cardNumber.point
    }

    fun getSuitName(): String {
        return suit.getName()
    }

    fun getCardNumber(): CardNumber {
        return cardNumber
    }

    override fun toString(): String {
        return "${cardNumber.cardName}${suit.koName}"
    }
}
