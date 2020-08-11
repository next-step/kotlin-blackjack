package blackjack

enum class CardNumbers(val score: Int, val shape: String) {
    ACE(1, "A"),
    TWO(2, "2"),
    THREE(3, "3"),
    FOUR(3, "4"),
    FIVE(3, "5"),
    SIX(3, "6"),
    SEVEN(3, "7"),
    EIGHT(3, "8"),
    NINE(3, "9"),
    TEN(3, "10"),
    KING(3, "K"),
    QUEEN(3, "Q"),
    JACK(3, "J");
}
