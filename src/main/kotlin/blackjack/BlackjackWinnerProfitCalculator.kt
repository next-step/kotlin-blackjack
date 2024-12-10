package blackjack

class BlackjackWinnerProfitCalculator : BlackjackPlayerProfitCalculator {
    override fun calculateProfit(
        playerState: State,
        betAmount: Money,
    ): Money = playerState.profit(betAmount)
}
