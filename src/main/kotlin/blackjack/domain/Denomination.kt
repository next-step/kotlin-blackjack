package blackjack.domain

enum class Denomination(
    val score: Score,
    val displayName: String = score.score.toString()
) {
    TWO(Score.from(2)),
    THREE(Score.from(3)),
    FOUR(Score.from(4)),
    FIVE(Score.from(5)),
    SIX(Score.from(6)),
    SEVEN(Score.from(7)),
    EIGHT(Score.from(8)),
    NINE(Score.from(9)),
    TEN(Score.from(10)),
    ACE(Score.from(1), displayName = "A"),
    KING(Score.from(10), displayName = "K"),
    QUEEN(Score.from(10), displayName = "Q"),
    JACK(Score.from(10), displayName = "J");
}
