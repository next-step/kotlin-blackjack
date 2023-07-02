package blackjack

data class Card(
    val suit: Suit,
    val rank: Rank
) {
    override fun toString(): String {
        return "${rank.nickname}${suit.korName}"
    }

    companion object {
        fun of(cardString: String): Card {
            val suit = Suit.of(cardString)
            val rank = Rank.of(cardString)
            return Card(suit, rank)
        }
    }
}
