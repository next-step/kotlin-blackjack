package domain.player

import exception.IllegalBetException

@JvmInline
value class BetAmount(val money: Int = DEFAULT_MONEY) {
    init {
        if (money < 1) {
            throw IllegalBetException()
        }
    }

    operator fun times(earningRate: Double) = earningRate * money

    companion object {
        private const val DEFAULT_MONEY = 1
    }
}
