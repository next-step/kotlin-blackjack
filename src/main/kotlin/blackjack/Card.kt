package blackjack

data class Card(val rankName: String, val suit: Suit) {
    val rank: CardRank = CardRank.from(rankName)
    val name = "$rankName${suit.displayName}"

    fun score(otherScore: Int): Int {
        return rank.calculateScore(otherScore)
    }
}
