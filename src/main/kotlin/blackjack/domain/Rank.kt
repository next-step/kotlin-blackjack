package blackjack.domain

enum class Rank(val value: Int, val rankName: String) {
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
    ACE(11, "A"), // ACE is 11, but can also be 1
}

enum class Suit(val koreanName: String) {
    HEARTS("하트"),
    DIAMONDS("다이아몬드"),
    CLUBS("클로바"),
    SPADES("스페이드"),
}
