package blackjack.domain

data class Card(val suit: Suit, val rank: Rank) {
    val possibleScore: PossibleScore = PossibleScore.getPossibleScore(rank)

    fun getPossibleScoreSums(score: Score): List<Score> = possibleScore.getPossibleScoreSums(score)
}

enum class Suit {
    HEART,
    DIAMOND,
    SPADE,
    CLUB;
}

enum class Rank(val number: Int) {
    ACE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8),
    NINE(9),
    TEN(10),
    JACK(11),
    QUEEN(12),
    KING(13);
}

@JvmInline
value class PossibleScore(private val values: Set<Score>) {

    fun getPossibleScoreSums(score: Score): List<Score> = values.map { score + it }

    companion object {
        fun getPossibleScore(rank: Rank) = when (rank) {
            Rank.ACE -> listOf(1, 11)
            Rank.TWO -> listOf(2)
            Rank.THREE -> listOf(3)
            Rank.FOUR -> listOf(4)
            Rank.FIVE -> listOf(5)
            Rank.SIX -> listOf(6)
            Rank.SEVEN -> listOf(7)
            Rank.EIGHT -> listOf(8)
            Rank.NINE -> listOf(9)
            Rank.TEN -> listOf(10)
            Rank.JACK -> listOf(10)
            Rank.QUEEN -> listOf(10)
            Rank.KING -> listOf(10)
        }
            .map(Score::from)
            .toSet()
            .let(::PossibleScore)
    }
}
