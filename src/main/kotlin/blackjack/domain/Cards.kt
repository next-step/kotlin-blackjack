package blackjack.domain

import blackjack.model.CardShape
import blackjack.model.CardType

class Cards(
    private val _value: MutableList<Card> = mutableListOf(),
) {
    val value: List<Card>
        get() = _value.toList()

    val size: Int
        get() = _value.size

    fun shuffle() = _value.shuffle()

    companion object {
        val CARD_DECK: Cards = CardType.values()
            .flatMap { type -> CardShape.values().map { shape -> Card(type = type, shape = shape) } }
            .toMutableList()
            .let(::Cards)
    }
}
