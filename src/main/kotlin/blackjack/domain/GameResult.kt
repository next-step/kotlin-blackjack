package blackjack.domain

data class GameResult(
    val dealerScore: Int,
    val playerResults: List<PlayerGameResult>,
) {
    data class PlayerGameResult(
        val playerName: String,
        val result: Result,
    )

    enum class Result {
        WIN,
        LOSE,
    }

    fun getDealerWinLoss(): Pair<Int, Int> {
        val dealerWins = playerResults.count { it.result == Result.LOSE }
        val dealerLosses = playerResults.size - dealerWins
        return dealerWins to dealerLosses
    }
}
