package blackjack.domain.player

import blackjack.error.InvalidMoneyRangeException
import kotlin.math.floor

@JvmInline
value class BetAmount(val amount: Int = MINIMUM_MONEY) {
    init {
        if (amount < MINIMUM_MONEY) {
            throw InvalidMoneyRangeException(amount)
        }
    }

    operator fun plus(other: BetAmount): BetAmount = BetAmount(amount + other.amount)

    fun winBet(resultRate: Double): BetAmount {
        return BetAmount(floor(amount * resultRate).toInt())
    }

    companion object {
        private const val MINIMUM_MONEY = 0
    }
}
