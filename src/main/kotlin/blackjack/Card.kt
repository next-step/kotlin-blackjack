package blackjack

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
enum class Rank {
    ACE,
    TWO,
    THREE,
    FOUR,
    FIVE,
    SIX,
    SEVEN,
    EIGHT,
    NINE,
    TEN,
    JACK,
    QUEEN,
    KING
}

enum class Suit {
    HEART,
    DIAMOND,
    CLUB,
    SPADE
}
