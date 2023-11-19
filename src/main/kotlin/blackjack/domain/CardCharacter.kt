package blackjack.domain

enum class CardCharacter(val scores: Pair<Int, Int>, val mark: String) {
    ACE(11 to 1, "A"),
    TWO(2 to 2, "2"),
    THREE(3 to 3, "3"),
    FOUR(4 to 4, "4"),
    FIVE(5 to 5, "5"),
    SIX(6 to 6, "6"),
    SEVEN(7 to 7, "7"),
    EIGHT(8 to 8, "8"),
    NINE(9 to 9, "9"),
    TEN(10 to 10, "10"),
    JACK(10 to 10, "J"),
    QUEEN(10 to 10, "Q"),
    KING(10 to 10, "K");
}
