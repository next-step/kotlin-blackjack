package blackjack.model

import java.math.BigDecimal

class Money(private val value: BigDecimal) {

    val negative: Money get() = Money(value.abs().negate())

    val oneAndHalfTimes: Money get() = Money(value.multiply(BigDecimal.valueOf(1.5)))

    constructor(value: Int) : this(value.toBigDecimal())

    operator fun plus(other: Money): Money {
        return Money(value + other.value)
    }

    operator fun unaryMinus(): Money = Money(value.negate())

    override fun toString(): String {
        return value.stripTrailingZeros().toPlainString()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Money

        return value.compareTo(other.value) == 0
    }

    override fun hashCode(): Int {
        return value.hashCode()
    }

    companion object {
        val ZERO: Money = Money(0)
    }
}
