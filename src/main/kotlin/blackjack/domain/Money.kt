package blackjack.domain

@JvmInline
value class Money(private val amount: Int) {
    operator fun unaryMinus(): Money = Money(-amount)
    operator fun times(value: Double): Money = Money((amount * value).toInt())
}
