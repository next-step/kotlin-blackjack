package blackjack.domain

data class Card(val suit: Suit, val rank: Rank, val possibleScore: PossibleScore) {
    companion object {

        private val cardPool: Set<Card> =
            Suit.values().flatMap { suit ->
                Rank.values().map { rank ->
                    Card(suit = suit, rank = rank, possibleScore = PossibleScore.getPossibleScore(rank))
                }
            }.toSet()

        fun getDeck(): Deck = cardPool.toMutableSet()
    }
}

typealias Deck = MutableSet<Card>

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
value class PossibleScore(val values: Set<Int>) {
    companion object {
        fun getPossibleScore(rank: Rank) = when (rank) {
            Rank.ACE -> setOf(1, 11)
            Rank.TWO -> setOf(2)
            Rank.THREE -> setOf(3)
            Rank.FOUR -> setOf(4)
            Rank.FIVE -> setOf(5)
            Rank.SIX -> setOf(6)
            Rank.SEVEN -> setOf(7)
            Rank.EIGHT -> setOf(8)
            Rank.NINE -> setOf(9)
            Rank.TEN -> setOf(10)
            Rank.JACK -> setOf(10)
            Rank.QUEEN -> setOf(10)
            Rank.KING -> setOf(10)
        }.let { PossibleScore(it) }
    }
}
