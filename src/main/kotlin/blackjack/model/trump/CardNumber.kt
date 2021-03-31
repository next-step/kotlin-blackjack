package blackjack.model.trump

import blackjack.model.score.Scores

enum class CardNumber(private val symbol: String, val scores: Scores) {
    ACE("A", Scores(1, 11)),
    TWO("2", Scores(2)),
    THREE("3", Scores(3)),
    FOUR("4", Scores(4)),
    FIVE("5", Scores(5)),
    SIX("6", Scores(6)),
    SEVEN("7", Scores(7)),
    EIGHT("8", Scores(8)),
    NINE("9", Scores(9)),
    TEN("10", Scores(10)),
    JACK("J", Scores(10)),
    QUEEN("Q", Scores(10)),
    KING("K", Scores(10));

    override fun toString(): String {
        return symbol
    }
}
