package blackjack

class Card(
    val suit: Suit,
    val rank: Rank,
)

enum class Rank {
    ACE,
    TWO,
    THREE,
    FOUR,
    FIVE,
    SIX,
    SEVEN,
    EIGHT,
    NINE,
    TEN,
    JACK,
    QUEEN,
    KING
}

enum class Suit {
    HEART,
    DIAMOND,
    CLUB,
    SPADE
}
