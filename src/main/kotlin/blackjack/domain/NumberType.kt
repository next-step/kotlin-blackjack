package blackjack.domain

enum class NumberType(
    val score: CardScore,
    val nameType: String
) {
    ACE(CardScore(listOf(Score(1), Score(11))), "A"),
    TWO(CardScore.from(2), "2"),
    THREE(CardScore.from(3), "3"),
    FOUR(CardScore.from(4), "4"),
    FIVE(CardScore.from(5), "5"),
    SIX(CardScore.from(6), "6"),
    SEVEN(CardScore.from(7), "7"),
    EIGHT(CardScore.from(8), "8"),
    NINE(CardScore.from(9), "9"),
    TEN(CardScore.from(10), "10"),
    JACK(CardScore.from(10), "J"),
    QUEEN(CardScore.from(10), "Q"),
    KING(CardScore.from(10), "K")
}
