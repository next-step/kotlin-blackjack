package blackjack.domain

data class Card(val suit: SuitType, val value: ValueType) {
    fun getPoint(aceToBig: Boolean = true): Int = value.point
}
