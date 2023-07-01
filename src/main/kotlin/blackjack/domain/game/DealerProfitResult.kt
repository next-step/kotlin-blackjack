package blackjack.domain.game

@JvmInline
value class DealerProfitResult(val profit: Double) {

    companion object {

        fun create(playerProfitResults: List<PlayerProfitResult>): DealerProfitResult {
            return DealerProfitResult(profit = playerProfitResults.totalProfit() * -1)
        }
    }
}
