package blackjack.domain

import blackjack.enums.Type
import blackjack.enums.Value

data class Card(private val type: Type, private val value: Value) {
    val origin = value.origin
    val alternative = value.alternative

    override fun toString(): String {
        return "$value of ${type.name}"
    }
}
