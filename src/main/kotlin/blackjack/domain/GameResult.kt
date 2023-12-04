package blackjack.domain

data class GameResult(
    val dealerName: String,
    val playerResults: List<GameResultByPlayer>,
) {

    val dealerWinCount by lazy {
        playerResults.count { it.result == Result.LOSE }
    }

    val dealerDrawCount by lazy {
        playerResults.count { it.result == Result.DRAW }
    }

    val dealerLoseCount by lazy {
        playerResults.count { it.result == Result.WIN }
    }

    data class GameResultByPlayer(
        val playerName: String,
        val result: Result,
    )

    enum class Result {
        WIN, LOSE, DRAW
    }
}
