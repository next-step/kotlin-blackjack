package blackjack.domain

class GameResults(
    private val gameResults: List<GameResult>
): List<GameResult> by gameResults {
    fun getOpposite(): GameResults {
        return gameResults
            .map { it.opposite() }
            .toGameResults()
    }
}

fun List<GameResult>.toGameResults(): GameResults {
    return GameResults(this)
}
