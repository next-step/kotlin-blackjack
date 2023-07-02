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
        val CARD_POOL: List<Card> = Symbol.values().map { symbol ->
            Type.values().map { Card(it, symbol) }
        }
            .flatten()
    }
}

operator fun Int.plus(other: Card): Int {
    return other + this
}
