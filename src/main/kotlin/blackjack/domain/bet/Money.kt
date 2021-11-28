package blackjack.domain.bet

import blackjack.error.InvalidMoneyRangeException

@JvmInline
value class Money(val money: Int = MINIMUM_MONEY) {
    init {
        if (money < MINIMUM_MONEY) {
            throw InvalidMoneyRangeException(money)
        }
    }

    operator fun plus(other: Money): Money = Money(money + other.money)

    companion object {
        private const val MINIMUM_MONEY = 0
    }
}
