package blackjack.domain

data class Card(
    val suit: Suit,
    val rank: Rank,
) {
    fun calculateScore(): Pair<Int, Int> {
        if (rank == Rank.ACE) return Rank.ACE.score to ACE_SCORE
        return rank.score to rank.score
    }

    override fun toString(): String {
        return "${rank.rankName}${suit.suitName}"
    }

    companion object {
        private const val ACE_SCORE = 11
    }
}
