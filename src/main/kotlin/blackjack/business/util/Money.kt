package blackjack.business.util

data class Money(val value: Int = 0) {

    operator fun plus(money: Money): Money = Money(this.value + money.value)

    fun lose(): Money = Money(-value)

    operator fun times(value: Double): Money = Money((this.value * value).toInt())
}
