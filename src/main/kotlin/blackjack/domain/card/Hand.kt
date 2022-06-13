package blackjack.domain.card

import blackjack.domain.card.Denomination.ACE

data class Hand(
    private val cards: List<Card> = emptyList(),
) {
    private val _cards: MutableList<Card> = cards.toMutableList()

    fun calculate(): Point {
        val numberOfAce = _cards.count { it.denomination == ACE }
        val sumOfNotAce = _cards.filterNot { it.denomination == ACE }
            .fold(0) { sum, card ->
                sum + card.defaultPoint.value
            }
        return (0..numberOfAce).fold(Point(sumOfNotAce + numberOfAce)) { point, i ->
            val sum = ACE.run {
                Point(minValue * i + maxValue * (numberOfAce - i) + sumOfNotAce)
            }
            if (sum <= Point.BLACKJACK) {
                sum.compareAndMax(point)
            } else {
                point
            }
        }
    }

    fun add(card: Card) {
        _cards.add(card)
    }

    fun isNotEmpty(): Boolean = _cards.isNotEmpty()

    fun first(): Card = _cards.first()

    override fun toString(): String {
        return _cards.joinToString {
            it.toString()
        }
    }

    companion object {
        fun empty(): Hand = Hand(emptyList())
    }
}
