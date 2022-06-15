package blackjack.domain

@JvmInline
value class Money(private val amount: Int) {
    operator fun unaryMinus(): Money = Money(-amount)

    operator fun times(value: Double): Money = Money((amount * value).toInt())

    operator fun minus(money: Money): Money = Money(amount - money.amount)

    override fun toString(): String = amount.toString()
}
