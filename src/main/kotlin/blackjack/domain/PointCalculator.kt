package blackjack.domain

import blackjack.domain.model.CardValue
import blackjack.domain.model.Cards

object PointCalculator {
    fun sum(cards: Cards): Int {
        var sum = 0
        cards.items.filterNot { it.value == CardValue.ACE }.forEach {
            sum += it.value.toPoint(sum)
        }
        cards.items.filter { it.value == CardValue.ACE }.forEach {
            sum += it.value.toPoint(sum)
        }
        return sum
    }

    private fun CardValue.toPoint(sum: Int): Int {
        return when (this) {
            CardValue.TWO -> 2
            CardValue.THREE -> 3
            CardValue.FOUR -> 4
            CardValue.FIVE -> 5
            CardValue.SIX -> 6
            CardValue.SEVEN -> 7
            CardValue.EIGHT -> 8
            CardValue.NINE -> 9
            CardValue.ACE -> if (sum + 11 > Rule.BLACK_JACK) 1 else 11
            else -> 10
        }
    }
}
