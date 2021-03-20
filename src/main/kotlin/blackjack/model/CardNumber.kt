package blackjack.model

enum class CardNumber(private val symbol: String, val scores: Scores) {
    ACE("A", Scores.Builder().scoresFromInt(listOf(1, 11)).build()),
    TWO("2", Scores.Builder().scoresFromInt(listOf(2)).build()),
    THREE("3", Scores.Builder().scoresFromInt(listOf(3)).build()),
    FOUR("4", Scores.Builder().scoresFromInt(listOf(4)).build()),
    FIVE("5", Scores.Builder().scoresFromInt(listOf(5)).build()),
    SIX("6", Scores.Builder().scoresFromInt(listOf(6)).build()),
    SEVEN("7", Scores.Builder().scoresFromInt(listOf(7)).build()),
    EIGHT("8", Scores.Builder().scoresFromInt(listOf(8)).build()),
    NINE("9", Scores.Builder().scoresFromInt(listOf(9)).build()),
    TEN("10", Scores.Builder().scoresFromInt(listOf(10)).build()),
    JACK("J", Scores.Builder().scoresFromInt(listOf(10)).build()),
    QUEEN("Q", Scores.Builder().scoresFromInt(listOf(10)).build()),
    KING("K", Scores.Builder().scoresFromInt(listOf(10)).build());

    override fun toString(): String {
        return symbol
    }
}
