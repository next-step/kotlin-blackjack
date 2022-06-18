package blackjack.domain.card

import blackjack.domain.card.Denomination.ACE

class Hand(
    cards: List<Card> = emptyList(),
) {
    private val cards: MutableList<Card> = cards.toMutableList()

    fun calculate(): Point {
        val numberOfAce = cards.count { it.denomination == ACE }
        val sumOfNotAce = cards.filterNot { it.denomination == ACE }
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
        cards.add(card)
    }

    fun first(): Card = cards.first()

    override fun toString(): String {
        return cards.joinToString {
            it.toString()
        }
    }

    companion object {
        fun empty(): Hand = Hand(emptyList())
    }
}
