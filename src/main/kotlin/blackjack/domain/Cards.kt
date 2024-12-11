package blackjack.domain

import blackjack.domain.Card.Companion.ACE_VALUE_ONE
import blackjack.domain.Card.Companion.MAX_SUM

data class Cards(val value: List<Card>) {
    fun sumValues(): Int {
        val (nonAcesSum, aceCount) = calculateNonAcesAndCountAces()
        return adjustForAces(nonAcesSum, aceCount)
    }

    private fun calculateNonAcesAndCountAces(): Pair<Int, Int> {
        var sum = 0
        var aceCount = 0
        value.forEach { card ->
            if (card.rank == Rank.ACE) aceCount++ else sum += card.rank.value
        }
        return sum to aceCount
    }

    private fun adjustForAces(currentSum: Int, aceCount: Int): Int {
        var sum = currentSum
        repeat(aceCount) {
            sum += if (sum + Rank.ACE.value > MAX_SUM) ACE_VALUE_ONE else Rank.ACE.value
        }
        return sum
    }
}
