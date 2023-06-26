package blackjack.model

import java.math.BigDecimal

@JvmInline
value class Money(private val value: BigDecimal) {

    val negative: Money get() = Money(value.abs().negate())

    constructor(value: Int) : this(value.toBigDecimal())

    operator fun plus(other: Money): Money {
        return Money(value + other.value)
    }

    operator fun unaryMinus(): Money = Money(value.negate())

    override fun toString(): String {
        return value.stripTrailingZeros().toPlainString()
    }

    companion object {
        val ZERO: Money = Money(0)
    }
}
