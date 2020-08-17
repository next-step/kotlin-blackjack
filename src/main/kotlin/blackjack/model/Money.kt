package blackjack.model

import kotlin.math.abs

data class Money(var money: Int = 0) {
    fun plus(plus: Money) {
        money += plus.money
    }

    fun minus(minus: Money) {
        money -= minus.money
    }

    fun calculate(earningRate: Double): Money {
        return Money((abs(money) * earningRate).toInt())
    }
}
