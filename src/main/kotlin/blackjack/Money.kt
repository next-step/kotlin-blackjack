package blackjack

@JvmInline
value class Money(private val amount: Double) : Comparable<Money> {
    constructor(amount: Long) : this(amount.toDouble())

    operator fun plus(other: Money): Money {
        return Money(this.amount + other.amount)
    }

    operator fun minus(other: Money): Money {
        return Money(this.amount - other.amount)
    }

    override fun compareTo(other: Money): Int {
        return amount compareTo other.amount
    }

    operator fun times(multiplier: Double): Money {
        return Money(this.amount * multiplier)
    }

    operator fun unaryMinus(): Money {
        return Money(-amount)
    }

    fun toDouble(): Double {
        return amount
    }

    companion object {
        val ZERO = Money(0)
    }
}
