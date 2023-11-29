package blackjack.domain

const val BLACKJACK = 21

class GameResult(players: List<Player>, private val dealer: Dealer) {

    val playerResults: Map<Player, GameReward>
    val dealerResults: List<GameReward>

    init {
        playerResults = players.associateWith { calculateReward(it) }.toMutableMap()
        dealerResults = calculateDealerStats(players)
    }

    private fun calculateReward(player: Player, participant: Participant = player): GameReward {
        val state = GameOutcomeState.create(participant)
        val outcome = state.calculateOutcome(player, dealer)

        return GameReward.create(player.betAmount, outcome, player)
    }

    private fun calculateDealerStats(players: List<Player>): List<GameReward> {
        return players.map { calculateReward(it, dealer) }
    }
}
