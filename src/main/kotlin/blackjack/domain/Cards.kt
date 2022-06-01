package blackjack.domain

import blackjack.domain.Denomination.ACE
import java.lang.Integer.max

class Cards(
    cards: List<Card>
) {
    private val _cards: MutableList<Card> = mutableListOf()

    init {
        _cards.addAll(cards)
    }

    val point: Int
        get() {
            val numberOfAce = _cards.count { it.denomination == ACE }
            val sumOfNotAce = _cards.filterNot { it.denomination == ACE }
                .fold(0) { sum, card ->
                    sum + card.denomination.maxValue
                }
            val minPoint = sumOfNotAce + numberOfAce
            return (0..numberOfAce).fold(minPoint) { point, i ->
                val sum = ACE.run {
                    minValue * i + maxValue * (numberOfAce - i) + sumOfNotAce
                }
                if (sum <= 21) max(sum, point)
                else point
            }
        }
}
