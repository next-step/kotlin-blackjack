package blackjack

import blackjack.domain.PokerCard

const val ACE = "A"
const val BLACK_JACK_NUM = 21

object HandsCalculator {
    fun calculateOptimalValue(hand: List<PokerCard>): Int {
        var total = hand.sumOf { it.value }
        var aceCount = hand.count { it.rank == ACE }

        while (total > BLACK_JACK_NUM && aceCount > 0) {
            aceCount -= 1
            total -= 10
        }

        return total
    }
}
