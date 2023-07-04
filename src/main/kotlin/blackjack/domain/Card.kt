package blackjack.domain

data class Card(
    val rank: Rank,
    val suit: Suit
) {
    fun getScore(): Int {
        return rank.score
    }
}
