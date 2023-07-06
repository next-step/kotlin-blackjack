package blackjack.domain

class Card private constructor(val type: Type, val symbol: Symbol) {
    val value = symbol.value

    operator fun plus(other: Card): Int {
        return value + other.value
    }

    operator fun plus(other: Int): Int {
        if (symbol == Symbol.ACE && other > ACE_SPECIAL_VALUE_THRESHOLD) {
            return ACE_SPECIAL_VALUE + other
        }

        return value + other
    }

    companion object {
        const val ACE_SPECIAL_VALUE = 1
        const val ACE_SPECIAL_VALUE_THRESHOLD = 10
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

operator fun Int.plus(other: Card): Int {
    return other + this
}
