package blackjack.domain

class GameResult(private val players: List<Player>, private val dealer: Dealer) {

    private var dealerWins = 0
    private var dealerLosses = 0

    private val _playerResults: Map<Player, GameOutcome>
    val playerResults: Map<Player, GameOutcome>
        get() = _playerResults

    init {
        _playerResults = players.associateWith { calculateOutcome(it) }.toMutableMap()
        _playerResults.forEach { (_, outcome) ->
            when (outcome) {
                GameOutcome.WIN -> dealerLosses++
                GameOutcome.LOSE -> dealerWins++
            }
        }
    }

    private fun calculateOutcome(player: Player): GameOutcome {
        val playerScore = player.cards.calculateScore()
        val dealerScore = dealer.cards.calculateScore()

        return when {
            playerScore > BLACKJACK || (dealerScore in playerScore..BLACKJACK) -> GameOutcome.LOSE
            dealerScore > BLACKJACK || (playerScore in (dealerScore + 1)..BLACKJACK) -> GameOutcome.WIN
            else -> throw IllegalStateException("게임 점수가 올바르지 않습니다.")
        }
    }

    fun getDealerStats(): Pair<Int, Int> = dealerWins to dealerLosses

    companion object {
        private const val BLACKJACK = 21
    }
}

enum class GameOutcome {
    WIN, LOSE
}
