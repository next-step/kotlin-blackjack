package blackjack.domain

import blackjack.domain.CardShape
import blackjack.domain.CardType

class Card(
    private val shape: CardShape,
    val type: CardType
) {
    val point = type.point

    override fun toString(): String {
        return "${type.expression}${shape.expression}"
    }
}
