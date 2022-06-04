package blackjack.domain

import blackjack.enums.Type
import blackjack.enums.Value

class Card(val type: Type, val value: Value) {
    override fun toString(): String {
        return "$value of ${type.name}"
    }
}
