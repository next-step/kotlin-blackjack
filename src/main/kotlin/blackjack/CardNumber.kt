package blackjack

enum class CardNumber(val score: Score, val symbol: String) {
    ACE(Score(1), "A"),
    TWO(Score(2), "2"),
    THREE(Score(3), "3"),
    FOUR(Score(4), "4"),
    FIVE(Score(5), "5"),
    SIX(Score(6), "6"),
    SEVEN(Score(7), "7"),
    EIGHT(Score(8), "8"),
    NINE(Score(9), "9"),
    TEN(Score(10), "10"),
    JACK(Score(10), "J"),
    QUEEN(Score(10), "Q"),
    KING(Score(10), "K");
}
