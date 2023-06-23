package blackjack.domain

data class Card(val suit: Suit, val cardNumber: CardNumber) {
    val points: List<Int>
        get() = cardNumber.points
}

enum class Suit {
    SPADE, HEART, DIAMOND, CLOVER
}
