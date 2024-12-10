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

fun List<Card>.sumValues(): Int {
    var sum = 0
    var numAce = 0

    this.forEach { card ->
        if (card.rank == Rank.ACE) {
            numAce++
        } else {
            sum += card.rank.value
        }
    }
    // ACE 의 경우 합이 21 을 초과한다면 1로 간주하고, 합이 21을 초과하지 않는 경우 11로 간주
    repeat(numAce) {
        sum += if (sum + Rank.ACE.value > Card.MAX_SUM) Card.ACE_VALUE_ONE else Rank.ACE.value
    }
    return sum
}
