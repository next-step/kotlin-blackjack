package blackjack.model

import kotlin.math.abs

data class Money(var money: Int = 0) {
    fun add(addMoney: Money) {
        money += addMoney.money
    }

    fun subtract(subtractMoney: Money) {
        money -= subtractMoney.money
    }

    fun calculate(earningRate: Double): Money {
        return Money((abs(money) * earningRate).toInt())
    }
}
