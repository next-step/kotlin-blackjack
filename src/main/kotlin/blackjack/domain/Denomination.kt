package blackjack.domain

enum class Denomination(
    val score: Score,
    val displayName: String = score.score.toString()
) {
    TWO(Score(2)),
    THREE(Score(3)),
    FOUR(Score(4)),
    FIVE(Score(5)),
    SIX(Score(6)),
    SEVEN(Score(7)),
    EIGHT(Score(8)),
    NINE(Score(9)),
    TEN(Score(10)),
    ACE(Score(1), displayName = "A"),
    KING(Score(10), displayName = "K"),
    QUEEN(Score(10), displayName = "Q"),
    JACK(Score(10), displayName = "J");
}
