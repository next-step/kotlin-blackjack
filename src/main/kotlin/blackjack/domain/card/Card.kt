package blackjack.domain.card

class Card private constructor(val rank: Rank, val suit: Suit) {
    companion object {
        val CARD_PACK_CACHE: Map<String, Card> =
            Rank.VALUES.flatMap { rank -> Suit.values().map { rank combine it } }.toMap()

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

sealed class Rank(val score: Int, val symbol: String) {
    open fun getScoreDifference(): Int {
        return 0
    }

    companion object {
        val VALUES = listOf(ACE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING)
    }
}

object ACE : Rank(11, "A") {
    override fun getScoreDifference(): Int {
        return 10
    }
}

object TWO : Rank(2, "2")
object THREE : Rank(3, "3")
object FOUR : Rank(4, "4")
object FIVE : Rank(5, "5")
object SIX : Rank(6, "6")
object SEVEN : Rank(7, "7")
object EIGHT : Rank(8, "8")
object NINE : Rank(9, "9")
object TEN : Rank(10, "10")
object JACK : Rank(10, "J")
object QUEEN : Rank(10, "Q")
object KING : Rank(10, "K")

enum class Suit(val symbol: String) {
    SPADE("♠"),
    CLUB("♣"),
    DIAMOND("♦"),
    HEART("♥")
}
