package blackjack.domain

/**
 * Created by Jaesungchi on 2022.06.19..
 */
@JvmInline
value class Money(val value: Int = 0) {
    operator fun plus(money: Int): Money {
        return Money(value + money)
    }

    operator fun plus(money: Money): Money {
        return Money(value + money.value)
    }

    operator fun minus(money: Money): Money {
        return Money(value - money.value)
    }
}
