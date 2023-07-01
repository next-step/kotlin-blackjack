package blackjack.domain

data class Card(
    val rank: Rank = Rank.random(),
    val suit: Suit = Suit.random()
) {
    fun getScore(): Int {
        return rank.score
    }
}