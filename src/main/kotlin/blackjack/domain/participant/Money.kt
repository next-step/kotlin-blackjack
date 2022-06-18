package blackjack.domain.participant

@JvmInline
value class Money(val amount: Int) {

    operator fun plus(other: Money): Money = Money(amount + other.amount)
    operator fun minus(other: Money): Money = Money(amount - other.amount)
    operator fun times(value: Double): Money = Money((amount * value).toInt())
    operator fun compareTo(other: Money): Int {
        return amount compareTo other.amount
    }

    companion object {
        val ZERO = Money(0)
    }
}
