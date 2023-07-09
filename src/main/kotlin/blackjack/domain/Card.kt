package blackjack.domain

class Card private constructor(val type: Type, val symbol: Symbol) {
    val value = symbol.value

    companion object {
        val CARD_DECK: Map<String, Card> = Symbol.values().map { symbol ->
            Type.values().map { Card(it, symbol) }
        }
            .flatten()
            .associateBy { "${it.type.name}${it.symbol.name}" }

        fun of(type: Type, symbol: Symbol): Card {
            val card = CARD_DECK["${type.name}${symbol.name}"]
            require(card != null) { "type:${type.name} 과 symbol:${symbol.name} 에 일치하는 카드가 없습니다." }

            return card
        }
    }
}
