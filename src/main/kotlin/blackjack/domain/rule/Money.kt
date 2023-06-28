package blackjack.domain.rule

@JvmInline
value class Money(val value: Int = 0) {
    operator fun plus(other: Money): Money {
        return Money(value + other.value)
    }

    operator fun minus(other: Money): Money {
        return Money(value - other.value)
    }
}
