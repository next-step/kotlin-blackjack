package blackjack.domain

data class Card(val rank: Rank, val suit: Suit) {
    private fun isAce() = rank == Rank.ACE

    companion object {
        const val ACE_VALUE_ONE = 1
        const val MAX_SUM = 21
    }
}
