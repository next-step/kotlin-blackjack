package blackjack.domain.bet

@JvmInline
value class Bet(private val bet: Double) {
    init {
        require(bet > 0) { BET_SHOULD_BE_ABOVE_ZERO }
    }

    fun blackjackWin(): Profit = Profit(bet * BLACKJACK_WIN_PROFIT_MULTIPLIER)

    fun win(): Profit = Profit(bet)

    fun push(): Profit = Profit(0.0)

    fun lose(): Profit = Profit(-bet)

    companion object {
        private const val BET_SHOULD_BE_ABOVE_ZERO = "베팅 금액은 0보다 큰 숫자이여야 합니다."
        private const val BLACKJACK_WIN_PROFIT_MULTIPLIER = 1.5
    }
}
