package blackjack.dto

@JvmInline
value class Money(val money: Int) {

    infix operator fun times(times: Double): Money = Money((money * times).toInt())

    infix operator fun times(times: Int): Money = Money(money * times)

    infix operator fun minus(minus: Money): Money = Money(money - minus.money)
}
