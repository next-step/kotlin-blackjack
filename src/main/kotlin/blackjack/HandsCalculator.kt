package blackjack

import blackjack.domain.PokerCard

private const val ACE = "A"
private const val ACE_ALT_VALUE = 10
private const val ACE_THRESHOLD = 11

object HandsCalculator {
    fun calculateOptimalValue(hand: List<PokerCard>): Int {
        val total = hand.sumOf { it.value }
        val aceCount = hand.count { it.rank == ACE }

        return calculateValueWithAce(total, aceCount)
    }

    private fun calculateValueWithAce(total: Int, aceCount: Int): Int {
        if (aceCount == 0) return total

        val optimalTotal = total - (aceCount * ACE_ALT_VALUE)

        if (optimalTotal <= ACE_THRESHOLD) {
            return optimalTotal + ACE_ALT_VALUE
        }

        return optimalTotal
    }
}
