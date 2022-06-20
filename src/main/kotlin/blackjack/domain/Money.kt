package blackjack.domain

@JvmInline
value class Money(private val amount: Int) {
    operator fun unaryMinus(): Money = Money(-amount)

    operator fun times(value: Float): Money = Money((amount * value).toInt())

    operator fun plus(money: Money): Money = Money(amount + money.amount)

    override fun toString(): String = amount.toString()
}
