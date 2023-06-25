package blackjack.domain

data class Card(val suit: Suit, val cardNumber: CardNumber)

enum class Suit {
    SPADE, HEART, DIAMOND, CLOVER
}
