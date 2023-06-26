package blackjack.domain

import blackjack.domain.Rule.BLACK_JACK
import blackjack.domain.model.CardValue.Companion.isAce
import blackjack.domain.model.Cards

object PointCalculator {
    private const val ACE_BONUS_POINT = 10

    fun sum(cards: Cards): Int {
        var sum = 0
        cards.items.filterNot { it.value.isAce() }.forEach {
            sum += it.value.point
        }
        cards.items.filter { it.value.isAce() }.forEach {
            sum += it.value.point
            if (sum + ACE_BONUS_POINT <= BLACK_JACK) sum += ACE_BONUS_POINT
        }
        return sum
    }
}
