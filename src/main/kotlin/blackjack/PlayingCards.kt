package blackjack

import blackjack.Point.Companion.ACE
import blackjack.Point.Companion.MAX
import blackjack.Point.Companion.SPECIAL_ACE
import blackjack.Point.Companion.SPECIAL_ACE_USABLE_BOUNDARY
import blackjack.Point.Companion.ZERO

class PlayingCards(
    private val _cards: MutableSet<Card> = mutableSetOf(),
) {
    val cards: Set<Card>
        get() = _cards.toSet()

    fun calculatePoint(): Point {
        val sumPoint = sumPoint()
        return if (sumPoint > MAX) ZERO else sumPoint
    }

    private fun sumPoint(): Point {
        if (noAce()) return _cards.sumOf { it.point }

        val sumWithoutSpecialAce = _cards.sumOf { it.point } - ACE
        if (sumWithoutSpecialAce > SPECIAL_ACE_USABLE_BOUNDARY) {
            return sumWithoutSpecialAce + ACE
        }
        return sumWithoutSpecialAce + SPECIAL_ACE
    }

    private fun noAce() = !_cards.any { it.isAce() }

    fun addOne(card: Card) {
        _cards.add(card)
    }

    fun addAll(cards: Set<Card>) {
        this._cards.addAll(cards)
    }
}

private fun <E> Set<E>.sumOf(function: (E) -> Point): Point {
    var sum = Point(0)
    for (element in this) {
        sum += function(element)
    }
    return sum
}

