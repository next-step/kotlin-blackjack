package domain.player

import exception.IllegalBetException

@JvmInline
value class BetAmount(val money: Int = ZERO_MONEY) {
    init {
        if (money < ZERO_MONEY) {
            throw IllegalBetException()
        }
    }

    operator fun times(earningRate: Double): Double = earningRate * money

    companion object {
        private const val ZERO_MONEY = 0
    }
}
