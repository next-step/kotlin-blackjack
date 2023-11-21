package blackjack.domain

const val BLACKJACK = 21

class GameResult(players: List<Player>, private val dealer: Dealer) {

    val playerResults: Map<Player, GameOutcome>
    val dealerStats: DealerStats

    init {
        playerResults = players.associateWith { player -> calculateOutcome(player) }.toMutableMap()
        dealerStats = DealerStats(
            wins = playerResults.filter { it.value == GameOutcome.LOSE }.count(),
            losses = playerResults.filter { it.value == GameOutcome.WIN }.count()
        )
    }

    private fun calculateOutcome(player: Player): GameOutcome {
        val playerScore = player.calculateScore()
        val dealerScore = dealer.calculateScore()

        return when {
            playerScore > BLACKJACK || (dealerScore in playerScore..BLACKJACK) -> GameOutcome.LOSE
            dealerScore > BLACKJACK || (playerScore in (dealerScore + 1)..BLACKJACK) -> GameOutcome.WIN
            else -> throw IllegalStateException("게임 점수가 올바르지 않습니다.")
        }
    }
}

enum class GameOutcome {
    WIN, LOSE
}

data class DealerStats(val wins: Int, val losses: Int)
