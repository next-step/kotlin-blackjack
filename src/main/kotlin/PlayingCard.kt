import CardNumber.Companion.getPoint
import Suit.Companion.getName

class PlayingCard(val suit: Suit, val cardNumber: CardNumber) {
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

    companion object {
        private const val ADD_ACE_NUMBER = 10
        private const val ACE_POINT = 1
    }
}
