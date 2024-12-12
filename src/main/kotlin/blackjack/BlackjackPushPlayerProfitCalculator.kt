package blackjack

class BlackjackPushPlayerProfitCalculator : BlackjackPlayerProfitCalculator {
    override fun calculateProfit(
        playerState: State,
        betAmount: Money,
    ): Money = Money.ZERO
}
