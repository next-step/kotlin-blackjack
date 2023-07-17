package blackjack.domain

class Card private constructor(val type: Type, val symbol: Symbol) {
    val value = symbol.value

    companion object {
        val CARD_DECK: Map<String, Card> = Symbol.values().map { symbol ->
            Type.values().map { Card(it, symbol) }
        }
            .flatten()
            .associateBy { "${it.type.name}${it.symbol.name}" }
    }
}
