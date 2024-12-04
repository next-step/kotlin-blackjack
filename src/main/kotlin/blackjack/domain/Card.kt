package blackjack.domain

data class Card(
    val rank: CardRank,
    val suit: Suit,
) {
    fun score(otherScore: Int): Int {
        return rank.calculateScore(otherScore)
    }

    companion object {
        fun of(
            rankName: String,
            suit: Suit,
        ): Card {
            return Card(CardRank.valueOf(rankName), suit)
        }
    }
}
