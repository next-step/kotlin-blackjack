package blackjack.domain

@JvmInline
value class Money(val amount: Int = 0) {

    operator fun plus(money: Money): Money {
        return Money(amount + money.amount)
    }

    operator fun times(multiplier: Double): Money {
        return Money((amount * multiplier).toInt())
    }

    fun opposite(): Money {
        return Money(-amount)
    }
}
