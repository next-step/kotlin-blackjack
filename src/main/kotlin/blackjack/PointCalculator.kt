package blackjack

import blackjack.CardValue.Companion.toPoint

object PointCalculator {
    fun sum(cards: List<Card>): Int {
        var sum = 0
        cards.filterNot { it.value == CardValue.ACE }.forEach {
            sum += it.value.toPoint(sum)
        }
        cards.filter { it.value == CardValue.ACE }.forEach {
            sum += it.value.toPoint(sum)
        }
        return sum
    }
}
