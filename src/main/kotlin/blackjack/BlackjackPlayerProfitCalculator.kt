package blackjack

interface BlackjackPlayerProfitCalculator {
    fun calculateProfit(
        playerState: State,
        betAmount: Money,
    ): Money
}
