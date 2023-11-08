package blackjack.domain

data class Card(
    val suit: Suit,
    val rank: Rank,
) {
    fun calculateScore(): Pair<Int, Int> {
        if (rank == Rank.ACE) return 1 to 11
        return rank.score to rank.score
    }
}
