package blackjack.domain.card

class Card private constructor(val rank: Rank, val suit: Suit) {
    companion object {
        val CARD_PACK_CACHE: Map<String, Card> =
            Rank.values().flatMap { rank -> Suit.values().map { rank combine it } }.toMap()

        fun of(rank: Rank, suit: Suit): Card {
            return CARD_PACK_CACHE[key(rank, suit)]!!
        }

        private infix fun Rank.combine(suit: Suit): Pair<String, Card> {
            return key(this, suit) to Card(this, suit)
        }

        private fun key(rank: Rank, suit: Suit): String {
            return rank.symbol + suit.symbol
        }
    }
}
enum class Rank(val score: Int, val symbol: String) {
    ACE(11, "A"),
    TWO(2, "2"),
    THREE(3, "3"),
    FOUR(4, "4"),
    FIVE(5, "5"),
    SIX(6, "6"),
    SEVEN(7, "7"),
    EIGHT(8, "8"),
    NINE(9, "9"),
    TEN(10, "10"),
    JACK(10, "J"),
    QUEEN(10, "Q"),
    KING(10, "K")
}

enum class Suit(val symbol: String) {
    SPADE("♠"),
    CLUB("♣"),
    DIAMOND("♦"),
    HEART("♥")
}
