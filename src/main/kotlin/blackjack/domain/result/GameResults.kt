package blackjack.domain.result

data class GameResults(
    val playerResults: List<PlayerResult>,
    val dealerResult: DealerResult
) {
    fun playerWin(playerResult: PlayerResult): GameResults {
        return copy(
            playerResults = playerResults.plus(playerResult),
            dealerResult = dealerResult.dealerFinalRevenue(playerResult.finalRevenue)
        )
    }

    fun playerLose(playerResult: PlayerResult): GameResults {
        return copy(
            playerResults = playerResults.plus(playerResult),
            dealerResult = dealerResult.dealerFinalRevenue(playerResult.finalRevenue)
        )
    }
}
