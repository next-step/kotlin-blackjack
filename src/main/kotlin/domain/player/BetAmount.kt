package domain.player

import exception.IllegalBetException

@JvmInline
value class BetAmount(val money: Int = DEFAULT_MONEY) {
    init {
        if (money < 0) {
            throw IllegalBetException()
        }
    }

    operator fun times(earningRate: Double): Double = earningRate * money

    companion object {
        private const val DEFAULT_MONEY = 0
    }
}
