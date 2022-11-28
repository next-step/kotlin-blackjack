package blackjack

import blackjack.Point.Companion.ACE
import blackjack.Point.Companion.MAX
import blackjack.Point.Companion.SPECIAL_ACE
import blackjack.Point.Companion.SPECIAL_ACE_USABLE_BOUNDARY

class PlayingCards(
    val cards: MutableSet<Card> = mutableSetOf(),
) {
    fun calculatePoint(): Point {
        val sumPoint = sumPoint()
        if (sumPoint > MAX)
            return Point.ZERO
        return sumPoint
    }

    private fun sumPoint(): Point {
        if (noAce()) return cards.sumOf { it.point }

        val sumWithoutSpecialAce = cards.sumOf { it.point } - ACE
        if (sumWithoutSpecialAce > SPECIAL_ACE_USABLE_BOUNDARY) {
            return sumWithoutSpecialAce + ACE
        }
        return sumWithoutSpecialAce + SPECIAL_ACE
    }

    private fun noAce() = !cards.any { it.isAce() }

    fun addOne(card: Card) {
        cards.add(card)
    }

    fun addAll(cards: Set<Card>) {
        this.cards.addAll(cards)
    }
}

private fun <E> Set<E>.sumOf(function: (E) -> Point): Point {
    var sum = Point(0)
    for (element in this) {
        sum += function(element)
    }
    return sum
}

