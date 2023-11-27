package card

import card.CardNumber.Companion.getCardNumber
import card.CardNumber.Companion.getPoint
import card.Suit.Companion.getName

class PlayingCard(private val suit: Suit, private val cardNumber: CardNumber) {
    fun getPoint(): Int {
        return cardNumber.getPoint()
    }

    fun getSuitName(): String {
        return suit.getName()
    }

    fun getCardNumber(): CardNumber {
        return cardNumber
    }

    override fun toString(): String {
        return "${cardNumber.getCardNumber()}${suit.koName}"
    }
}
