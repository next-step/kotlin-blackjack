package blackjack.domain

data class PlayerResult(val user: User, val result: ResultStatus) {
    val profit: Profit
        get() = getProfit(user, result)

    private fun getProfit(user: User, status: ResultStatus): Profit {
        return when (status) {
            ResultStatus.WIN -> calculateProfitWhenUserWin(user)
            ResultStatus.LOSE -> Profit(-user.betAmount.value)
            ResultStatus.DRAW -> Profit.ZERO
        }
    }

    private fun calculateProfitWhenUserWin(user: User): Profit {
        if (user.isBlackJack()) {
            return Profit((user.betAmount.value * RATE_OF_BLACK_JACK_PROFIT).toInt())
        }

        return Profit(user.betAmount.value)
    }

    companion object {
        private const val RATE_OF_BLACK_JACK_PROFIT = 1.5
    }
}
