package blackjack.domain

class GameResult(
    private val players: Players
) {
    val scoreMap: Map<Player, Int>
        get() = generateScoreMap()

    private fun generateScoreMap(): Map<Player, Int> {
        return players.values.associateWith { it.calculateScore() }
    }
}
