package blackjack.domain

class BlackJackResultManager(
    private val dealer: Dealer,
    private val players: Players,
) {
    fun getResult(): BlackJackResult {
        val dealerScore = dealer.cardsSum
        val playersWinLose =
            players.value.associateWith { player ->
                PlayerResultCalculator.calculate(dealerScore, player.cardsSum)
            }

        return BlackJackResult(PlayerToResultMap(playersWinLose))
    }
}

data class BlackJackResult(
    val playerToResultMap: PlayerToResultMap,
) {
    val dealerWinCount: Int
        get() = playerToResultMap.getPlayerLoseCounts()
    val dealerLoseCount: Int
        get() = playerToResultMap.getPlayerWinningCounts()
}

@JvmInline
value class PlayerToResultMap(val value: Map<Player, GameResult>) {
    fun getPlayerWinningCounts(): Int {
        return value.count { it.value == GameResult.WIN }
    }

    fun getPlayerLoseCounts(): Int {
        return value.count { it.value == GameResult.LOSE }
    }
}

enum class GameResult {
    WIN,
    LOSE,
}
