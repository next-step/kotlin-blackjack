package blackjack.domain

import blackjack.domain.CardNumber.*

data class Card(private val cardNumber: CardNumber, private val suit: Suit) {

    fun toInt(isAceEleven: Boolean = false): Int {
        return when (val number = cardNumber) {
            Ace -> if (isAceEleven) 11 else 1
            King, Queen, Jack -> 10
            else -> number.rawValue.toInt()
        }
    }

    override fun toString(): String {
        return "${cardNumber.rawValue}${suit.rawValue}"
    }
}
