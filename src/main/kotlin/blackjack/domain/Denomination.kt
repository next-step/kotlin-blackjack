package blackjack.domain

enum class Denomination(val scores: Scores, val symbol: String) {
    ACE(Scores(listOf(Score(1), Score(11))), "A"),
    TWO(Scores.from(2), "2"),
    THREE(Scores.from(3), "3"),
    FOUR(Scores.from(4), "4"),
    FIVE((Scores.from(5)), "5"),
    SIX(Scores.from(6), "6"),
    SEVEN(Scores.from(7), "7"),
    EIGHT(Scores.from(8), "8"),
    NINE(Scores.from(9), "9"),
    TEN(Scores.from(10), "10"),
    JACK(Scores.from(10), "J"),
    QUEEN((Scores.from(10)), "Q"),
    KING(Scores.from(10), "K"),
    ;

    companion object {
        fun valuesOf(card: Card) {
        }
    }
}
