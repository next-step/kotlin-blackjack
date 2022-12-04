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

enum class Suit(val rawValue: String) {

    Spade("스페이드"),
    Diamond("다이아몬드"),
    Heart("하트"),
    Clover("클로버")
    ;
}

enum class CardNumber(val rawValue: String) {

    Ace("A"),
    Two("2"),
    Three("3"),
    Four("4"),
    Five("5"),
    Six("6"),
    Seven("7"),
    Eight("8"),
    Nine("9"),
    TEN("10"),
    King("K"),
    Queen("Q"),
    Jack("J")
    ;
}
