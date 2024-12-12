package blackjack

class BlackjackLoserProfitCalculator : BlackjackPlayerProfitCalculator {
    override fun calculateProfit(
        playerState: State,
        betAmount: Money,
    ): Money =
        when (playerState) {
            is Bust -> playerState.profit(betAmount)
            is Stay -> -playerState.profit(betAmount)
            else -> throw IllegalStateException("플레이어가 패배할 수 있는 상태가 아닙니다: playerState=$playerState")
        }
}
