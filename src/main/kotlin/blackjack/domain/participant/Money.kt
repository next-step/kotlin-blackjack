package blackjack.domain.participant

@JvmInline
value class Money(private val amount: Int) {

    operator fun plus(other: Money): Money = Money(amount + other.amount)
    operator fun minus(other: Money): Money = Money(amount - other.amount)
    operator fun times(value: Double): Money = Money((amount * value).toInt())
    fun toInt(): Int = amount

    companion object {
        val ZERO = Money(0)
    }
}
