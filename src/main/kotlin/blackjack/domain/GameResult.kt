package blackjack.domain

const val MAX_SCORE = 21

class GameResult(
    private val players: Players
) {
    val scoreMap: Map<Player, Int>
        get() = generateScoreMap()

    private fun generateScoreMap(): Map<Player, Int> {
        return players.values.associateWith { it.calculateScore() }
    }
}
