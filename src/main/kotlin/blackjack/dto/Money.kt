package blackjack.dto

@JvmInline
value class Money(val money: Int) {

    operator fun times(times: Double): Money = Money((money * times).toInt())

    operator fun times(times: Int): Money = Money(money * times)

    operator fun minus(minus: Money): Money = Money(money - minus.money)

    companion object {
        val ZERO = Money(0)
    }
}
