package blackjack.domain

private const val MIN_NUMBER_VALUE = 2
private const val MAX_NUMBER_VALUE = 10

enum class ValueType(val point: Int) {
    A(1),
    TWO(2), THREE(3), FOUR(4), FIVE(5),
    SIX(6), SEVEN(7), EIGHT(8), NINE(9), TEN(10),
    J(10), Q(10), K(10);

    fun getPoint(aceToBig: Boolean = false): Int {
        return (if (this == A && aceToBig) MAX_NUMBER_VALUE else 0) + this.point
    }

    companion object {
        fun getValueType(value: Int): ValueType {
            require(value in MIN_NUMBER_VALUE..MAX_NUMBER_VALUE)
            return values().find { it.point == value }!!
        }

        fun getValueType(value: String): ValueType {
            require(value.length == 1 && values().map { it.name }.contains(value))
            return values().find { it.name == value }!!
        }
    }
}
