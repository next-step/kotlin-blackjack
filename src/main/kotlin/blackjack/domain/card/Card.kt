package blackjack.domain.card

import blackjack.domain.card.suit.CardSuit

data class Card(val suit: CardSuit, val cardNumber: Int) {
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

    companion object {
        private val CARD_NUMBER_RANGE = 1..13
        private const val MAXIMUM_VALUE_OF_CARD = 10
    }
}
