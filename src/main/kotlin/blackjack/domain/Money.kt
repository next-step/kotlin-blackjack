package blackjack.domain

data class Money(val value: Int) {
    fun evenMoney(): Money {
        return Money((value * EVEN_PRODUCT).toInt())
    }

    fun toNegative(): Money {
        if (value < 0) {
            return this
        }
        return Money(-value)
    }

    operator fun times(multiplier: Double): Money {
        return Money((value * multiplier).toInt())
    }

    operator fun minus(other: Money): Money {
        return Money(value - other.value)
    }

    operator fun plus(other: Money): Money {
        return Money(value + other.value)
    }

    operator fun unaryMinus(): Money {
        return Money(-value)
    }

    companion object {
        const val EVEN_PRODUCT = 1.5
        val ZERO: Money = Money(0)
    }
}
