package blackjack.domain

import blackjack.domain.Denomination.ACE
import java.lang.Integer.max

class Hand(
    cards: List<Card>
) {
    private val _cards: MutableList<Card> = cards.toMutableList()

    fun calculate(): Int {
        val numberOfAce = _cards.count { it.denomination == ACE }
        val sumOfNotAce = _cards.filterNot { it.denomination == ACE }
            .fold(0) { sum, card ->
                sum + card.defaultPoint
            }
        return (0..numberOfAce).fold(sumOfNotAce + numberOfAce) { point, i ->
            val sum = ACE.run {
                minValue * i + maxValue * (numberOfAce - i) + sumOfNotAce
            }
            if (sum <= BLACKJACK_POINT) max(sum, point)
            else point
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
        const val BLACKJACK_POINT = 21
        fun empty(): Hand = Hand(emptyList())
    }
}
