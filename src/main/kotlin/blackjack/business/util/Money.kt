package blackjack.business.util

data class Money(private val value: Int = 0) {
    operator fun plus(money: Money): Money {
        return Money(this.value + money.value)
    }

    fun lose(): Money {
        return Money(-value)
    }

    operator fun times(value: Double): Money {
        return Money((this.value * value).toInt())
    }
}
