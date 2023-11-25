package card

import card.CardNumber.Companion.getCardName
import card.CardNumber.Companion.getPoint
import card.Suit.Companion.getName

class PlayingCard(private val suit: Suit, private val cardNumber: CardNumber) {
    fun getPoint(isMaxAce: Boolean = false): Int {
        val point = cardNumber.getPoint()
        if (isMaxAce && point == ACE_POINT) {
            return point + ADD_ACE_NUMBER
        }
        return cardNumber.getPoint()
    }

    fun getSuitName(): String {
        return suit.getName()
    }

    override fun toString(): String {
        return "${cardNumber.getCardName()}${suit.koName}"
    }

    companion object {
        private const val ADD_ACE_NUMBER = 10
        private const val ACE_POINT = 1
    }
}
