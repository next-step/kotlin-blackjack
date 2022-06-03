package blackjack.domain

import blackjack.domain.Denomination.ACE
import java.lang.Integer.max

class Hand(
    cards: List<Card>
) {
    private val _cards: MutableList<Card> = cards.toMutableList()

    val point: Int
        get() {
            val numberOfAce = _cards.count { it.denomination == ACE }
            val sumOfNotAce = _cards.filterNot { it.denomination == ACE }
                .fold(0) { sum, card ->
                    sum + card.denomination.maxValue
                }
            return (0..numberOfAce).fold(sumOfNotAce + numberOfAce) { point, i ->
                val sum = ACE.run {
                    minValue * i + maxValue * (numberOfAce - i) + sumOfNotAce
                }
                if (sum <= 21) max(sum, point)
                else point
            }
        }

    fun add(card: Card) {
        _cards.add(card)
    }

    override fun toString(): String {
        return _cards.joinToString(", ") {
            it.toString()
        }
    }

    companion object {
        fun empty(): Hand = Hand(emptyList())
    }
}
