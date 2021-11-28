package blackjack.domain.player

import blackjack.error.InvalidMoneyRangeException

@JvmInline
value class BettingMoney(private val money: Int = MINIMUM_MONEY) {
    init {
        if (money < MINIMUM_MONEY) {
            throw InvalidMoneyRangeException(money)
        }
    }

    companion object {
        private const val MINIMUM_MONEY = 0
    }
}
