package blackjack.domain

class Card(val suit: Suit, val rank: Rank) {
    override fun toString(): String {
        return "${rank.title}${suit.title}"
    }

    companion object {
        fun valueOf(index: Int): Card {
            val suitIndex = index / Rank.values().size
            val rankIndex = index % Rank.values().size
            return Card(Suit.values()[suitIndex], Rank.values()[rankIndex])
        }
    }
}
