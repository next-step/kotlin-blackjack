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
        val playerScore = player.calculateScore()
        val dealerScore = dealer.calculateScore()

        val state = GameOutcomeState.create(participant)
        val outcome = state.calculateOutcome(playerScore, dealerScore)

        return GameReward(player.betAmount, outcome)
    }

    private fun calculateDealerStats(players: List<Player>): List<GameReward> {
        return players.map { calculateReward(it, dealer) }
    }
}
