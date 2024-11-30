package blackjack.domain

data class Card(val rank: Rank, val suit: Suit) {
    fun isAce(): Boolean {
        return rank == Rank.ACE
    }

    fun display(): String {
        return "${rank.symbol}${suit.symbol}"
    }

    companion object {
        val ALL_CARDS =
            Rank.entries.flatMap { rank ->
                Suit.entries.map { suit ->
                    Card(rank, suit)
                }
            }
    }
}
