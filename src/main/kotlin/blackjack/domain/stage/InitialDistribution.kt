package blackjack.domain.stage

import blackjack.domain.BlackJackGame
import blackjack.domain.result.InitialDistributionResult

class InitialDistribution(
    private val game: BlackJackGame,
) : Stage {
    override fun progress() {
        game.dealCardsToAllPlayers(INITIAL_DISTRIBUTION_COUNT)
    }

    override fun handleResult() {
        game.emitResult(InitialDistributionResult(game.players))
    }

    override fun nextStage(): Stage {
        TODO("Not yet implemented")
    }

    companion object {
        private const val INITIAL_DISTRIBUTION_COUNT = 2
    }
}
