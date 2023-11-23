package blackjack.domain

const val BLACKJACK = 21

class GameResult(players: List<Player>, private val dealer: Dealer) {

    val playerResults: Map<Player, GameOutcome>
    val dealerStats: DealerStats

    init {
        playerResults = players.associateWith { calculateOutcome(it) }.toMutableMap()
        dealerStats = calculateDealerStats(players)
    }

    private fun calculateOutcome(player: Player, participant: Participant = player): GameOutcome {
        val playerScore = player.calculateScore()
        val dealerScore = dealer.calculateScore()

        val state = GameOutcomeState.create(participant)
        return state.calculateOutcome(playerScore, dealerScore)
    }

    private fun calculateDealerStats(players: List<Player>): DealerStats {
        val gameOutcome = players.map { calculateOutcome(it, dealer) }
        return DealerStats(
            wins = gameOutcome.count { it == GameOutcome.WIN },
            losses = gameOutcome.count { it == GameOutcome.LOSE }
        )
    }
}

data class DealerStats(val wins: Int, val losses: Int)
