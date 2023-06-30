package blackjack.domain.rule

import kotlin.math.abs

@JvmInline
value class Money(val value: Int = 0) {

    fun getAmount(): Money {
        return Money(abs(value))
    }

    operator fun plus(other: Money): Money {
        return Money(value + other.value)
    }

    operator fun minus(other: Money): Money {
        return Money(value - other.value)
    }
}
