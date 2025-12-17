package domain

const val BLACKJACK_SCORE = 21

enum class Suit(val korean: String) {
    SPADE("스페이드"),
    HEART("하트"),
    DIAMOND("다이아몬드"),
    CLUB("클럽"),
}

enum class Rank(val value: Int, val symbol: String) {
    ACE(1, "A"),
    TWO(2, "2"),
    THREE(3, "3"),
    FOUR(4, "4"),
    FIVE(5, "5"),
    SIX(6, "6"),
    SEVEN(7, "7"),
    EIGHT(8, "8"),
    NINE(9, "9"),
    TEN(10, "10"),
    JACK(10, "J"),
    QUEEN(10, "Q"),
    KING(10, "K"),
}

data class Card(val suit: Suit, val rank: Rank) {
    override fun toString(): String {
        return "${rank.symbol}${suit.korean}"
    }
}
