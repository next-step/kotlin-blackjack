package blackjack.domain.status

enum class GameResult {
    WIN,
    LOSE,
    DRAW,
}

data class ResultRecord(
    private var winCount: Int = 0,
    private var loseCount: Int = 0,
) {
    fun updateResult(
        result: GameResult,
        count: Int = 1,
    ) {
        when (result) {
            GameResult.WIN -> winCount += count
            GameResult.LOSE -> loseCount += count
            GameResult.DRAW -> {}
        }
    }

    fun getWinCount(): Int {
        return winCount
    }

    fun getLoseCount(): Int {
        return loseCount
    }
}
