package blackjack.domain

class Card private constructor(
    val suit: Suit,
    val rank: Rank,
) {
    companion object {
        private val cache = mutableListOf<Card>()

        fun of(suit: Suit, rank: Rank): Card {
            val card = cache.find { it.suit == suit && it.rank == rank }
            return card ?: Card(suit, rank).also { cache.add(it) }
        }
    }
}
enum class Rank(val score: Int) {
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
    JACK(10),
    QUEEN(10),
    KING(10),
}

enum class Suit {
    HEART,
    DIAMOND,
    CLUB,
    SPADE,
}
