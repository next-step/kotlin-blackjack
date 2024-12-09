package blackjack.domain

data class GameResult(
    val dealerScore: Int,
    val results: Map<String, Result>,
) {
    enum class Result {
        WIN,
        LOSE,
    }

    fun getDealerWinLoss(): Pair<Int, Int> {
        val dealerWins = results.values.count { it == Result.LOSE }
        val dealerLosses = results.size - dealerWins
        return dealerWins to dealerLosses
    }
}
