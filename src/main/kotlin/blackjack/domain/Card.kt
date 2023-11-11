package blackjack.domain

enum class Suit {
    SPADE, HEART, DIAMOND, CLUB
}

enum class Value(val score: Int) {
    ACE(1), TWO(2), THREE(3), FOUR(4), FIVE(5),
    SIX(6), SEVEN(7), EIGHT(8), NINE(9), TEN(10),
    JACK(10), QUEEN(10), KING(10)
}

data class Card(val suit: Suit, val value: Value)

infix fun Suit.with(value: Value): Card = Card(this, value)
