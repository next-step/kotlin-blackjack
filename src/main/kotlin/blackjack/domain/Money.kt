package blackjack.domain

@JvmInline
value class Money(val value: Double = 0.0) {

    operator fun times(other: Double): Money = Money(value * other)
    operator fun plus(other: Double): Money = Money(value + other)
    operator fun plus(other: Money): Money = this + other.value
    operator fun minus(other: Double): Money = Money(value - other)
    operator fun minus(other: Money): Money = this - other.value

    override fun toString(): String {
        return value.toInt().toString()
    }
}
