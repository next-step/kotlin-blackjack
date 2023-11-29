package blackjack.domain

class Card(private val suit: Suit, private val rank: Rank) {
    fun score(): Int {
        return rank.score
    }

    override fun toString(): String {
        return "${rank.outputName}${suit.value}"
    }
}
