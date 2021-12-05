package blackjack.domain.card

import blackjack.domain.card.suit.SuitTypes

class Card(val suit: SuitTypes, cardNumber: Int) {
    val cardValue: Int
    val cardName: String

    init {
        require(cardNumber in CARD_NUMBER_RANGE)
        cardName = when (cardNumber) {
            11 -> "J"
            12 -> "Q"
            13 -> "K"
            else -> cardNumber.toString()
        }
        cardValue = cardNumber.coerceAtMost(MAXIMUM_VALUE_OF_CARD)
    }

    override fun equals(other: Any?): Boolean {
        if (other is Card)
            return other.suit == suit && other.cardName == cardName
        return false
    }

    override fun hashCode(): Int {
        var result = suit.hashCode()
        result = 31 * result + cardValue
        result = 31 * result + cardName.hashCode()
        return result
    }

    companion object {
        private val CARD_NUMBER_RANGE = 1..13
        private const val MAXIMUM_VALUE_OF_CARD = 10
    }
}
