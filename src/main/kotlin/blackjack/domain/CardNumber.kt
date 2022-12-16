package blackjack.domain

enum class CardNumber(
    displayName: String,
    vararg scores: Score
) {
    ACE("A", Score(1), Score(11)),
    TWO("2", Score(2)),
    THREE("3", Score(3)),
    FOUR("4", Score(4)),
    FIVE("5", Score(5)),
    SIX("6", Score(6)),
    SEVEN("7", Score(7)),
    EIGHT("8", Score(8)),
    NINE("9", Score(9)),
    TEN("10", Score(10)),
    JACK("J", Score(10)),
    QUEEN("Q", Score(10)),
    KING("K", Score(10))
}
