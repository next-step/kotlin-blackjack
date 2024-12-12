package blackjack.domain

data class Card(val rank: Rank, val suit: Suit) {
    private fun isAce() = rank == Rank.ACE

    fun isOverMaxSum(currentCardSum: Int): Boolean {
        return if (isAce()) {
            currentCardSum + ACE_VALUE_ONE > MAX_SUM && currentCardSum + this.rank.value > MAX_SUM
        } else {
            currentCardSum + this.rank.value > MAX_SUM
        }
    }

    companion object {
        const val ACE_VALUE_ONE = 1
        const val MAX_SUM = 21
    }
}
