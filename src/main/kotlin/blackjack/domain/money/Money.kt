package blackjack.domain.money

@JvmInline
value class BlackJackMoney(
    private val money: Long
) {
    fun get(): Long {
        return (money * BLACKJACK_DIVIDEND_RATE).toLong()
    }

    companion object {
        private const val BLACKJACK_DIVIDEND_RATE = 1.5
    }
}

@JvmInline
value class WinMoney(
    private val money: Long
) {
    fun get(): Long {
        return money
    }
}

@JvmInline
value class LoseMoney(
    private val money: Long
) {
    fun get(): Long {
        return (money * LOSE_DIVIDEND_RATE).toLong()
    }

    companion object {
        private const val LOSE_DIVIDEND_RATE = -1.0
    }
}
