package blackjack.domain

class GameResult(private val players: List<Player>, private val dealer: Dealer) {

    private val _playerResults: Map<Player, GameOutcome>
    val playerResults: Map<Player, GameOutcome>
        get() = _playerResults

    val dealerStats: DealerStats

    init {
        _playerResults = players.associateWith { calculateOutcome(it) }.toMutableMap()
        dealerStats = DealerStats(
            wins = _playerResults.filter { it.value == GameOutcome.LOSE }.count(),
            losses = _playerResults.filter { it.value == GameOutcome.WIN }.count()
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

    companion object {
        private const val BLACKJACK = 21
    }
}

enum class GameOutcome {
    WIN, LOSE
}

data class DealerStats(val wins: Int, val losses: Int)
