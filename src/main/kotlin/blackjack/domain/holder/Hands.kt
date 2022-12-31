package blackjack.domain.holder

import blackjack.domain.card.Card
import blackjack.domain.value.Point

data class Hands(
    private val _cards: MutableSet<Card> = mutableSetOf(),
) {
    val cards: Set<Card>
        get() = _cards.toSet()

    fun calculatePoint(): Point {
        val sumPoint = sumPoint()
        return if (sumPoint > Point.MAX) Point.ZERO else sumPoint
    }

    private fun sumPoint(): Point {
        if (hard()) return _cards.sumOf { it.point }
        return _cards.sumOf { it.point }
            .soft()
    }

    private fun hard() = !_cards.any { it.isAce() }

    fun addOne(card: Card) {
        _cards.add(card)
    }

    fun addAll(cards: Set<Card>) {
        this._cards.addAll(cards)
    }

    fun bust(): Boolean = sumPoint() > Point.MAX
    fun firstCard(): Set<Card> = setOf(_cards.first())

    fun blackJack() = cards.size == 2 && calculatePoint() == Point.BLACK_JACK
}

private fun <E> Set<E>.sumOf(function: (E) -> Point): Point {
    return this.map(function)
        .reduce { acc, point -> acc + point }
}
