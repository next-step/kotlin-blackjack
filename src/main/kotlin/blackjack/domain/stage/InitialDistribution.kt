package blackjack.domain.stage

import blackjack.domain.BlackJackGame
import blackjack.domain.result.InitialDistributionResult

class InitialDistribution(
    private val game: BlackJackGame,
) : Stage {
    var isProgressDone = false
        private set

    override fun progress() {
        game.dealCardsToAllPlayers(INITIAL_DISTRIBUTION_COUNT)
        isProgressDone = true
    }

    override fun emitResult() {
        game.emitResult(InitialDistributionResult(game.players))
    }

    override fun nextStage(): Stage = when (isProgressDone) {
        true -> InGame(game)
        false -> this
    }

    companion object {
        private const val INITIAL_DISTRIBUTION_COUNT = 2
    }
}
