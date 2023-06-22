package blackjack

import blackjack.domain.PokerCard

const val ACE = "A"

object HandsCalculator {
    fun calculateOptimalValue(hand: List<PokerCard>): Int {
        var total = hand.sumOf { it.value }
        var aceCount = hand.count { it.rank == ACE }

        while (total > 21 && aceCount > 0) {
            aceCount -= 1
            total -= 10
        }

        return total
    }
}
