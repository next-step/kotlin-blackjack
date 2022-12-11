package blackjack.domain

import blackjack.model.CardShape
import blackjack.model.CardType

class Cards(
    private val _value: MutableList<Card> = mutableListOf(),
) {
    val value: List<Card>
        get() = _value.toList()

    companion object {
        val CARD_DECK: List<Card> = CardType.values()
            .flatMap { type -> CardShape.values().map { shape -> Card(type = type, shape = shape) } }
    }
}
