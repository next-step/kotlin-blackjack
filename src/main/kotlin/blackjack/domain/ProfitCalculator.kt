package blackjack.domain

object ProfitCalculator {
    private const val RATE_OF_BLACK_JACK_PROFIT = 2.5
    private val DRAW_PROFIT = Profit(0)

    operator fun invoke(user: User, status: ResultStatus): Profit {
        return when (status) {
            ResultStatus.WIN -> calculateProfitWhenUserWin(user)
            ResultStatus.LOSE -> Profit(-user.betAmount.value)
            ResultStatus.DRAW -> DRAW_PROFIT
        }
    }

    private fun calculateProfitWhenUserWin(user: User): Profit {
        if (user.isBlackJack()) {
            return Profit((user.betAmount.value * RATE_OF_BLACK_JACK_PROFIT - user.betAmount.value).toInt())
        }

        return Profit(user.betAmount.value)
    }
}
