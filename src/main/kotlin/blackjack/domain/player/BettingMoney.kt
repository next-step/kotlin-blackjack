package blackjack.domain.player

import blackjack.error.InvalidMoneyRangeException
import kotlin.math.floor

@JvmInline
value class BettingMoney(val money: Int = MINIMUM_MONEY) {
    init {
        if (money < MINIMUM_MONEY) {
            throw InvalidMoneyRangeException(money)
        }
    }

    fun winBet(betRate: Double): BettingMoney {
        return BettingMoney(floor(money * betRate).toInt())
    }

    companion object {
        private const val MINIMUM_MONEY = 0
    }
}
