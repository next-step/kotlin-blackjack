package blackjack

class Card(rankName: String, val suit: CardSuit) {
    val rank: CardRank = CardRank.from(rankName)
    val name = "$rankName${suit.displayName}"

    fun score(otherScore: Int): Int {
        return rank.calculateScore(otherScore)
    }
}
