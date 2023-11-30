package blackJack.domain.result

class DealerResult(val dealerProfit: Int) {
    companion object {
        fun calculateResult(playersResults: PlayersResult): DealerResult {
            val dealerProfit = -playersResults.playersResult.sumOf {
                it.bettingPrice * it.result.reward
            }.toInt()
            return DealerResult(dealerProfit)
        }
    }
}
