package blackjack.domain.stage

import blackjack.controller.InputProcessor.returnResult
import blackjack.domain.BlackJackGame
import blackjack.domain.result.InitialDistributionResult

class InitialDistribution(
    private val game: BlackJackGame,
) : Stage {
    override fun receiveSetupData() {}

    override fun progress() {
        game.dealCardsToAllPlayers(INITIAL_DISTRIBUTION_COUNT)
    }

    override fun handleResult() {
        this.returnResult(InitialDistributionResult(game.players))
    }

    override fun nextStage(): Stage {
        TODO("Not yet implemented")
    }

    companion object {
        private const val INITIAL_DISTRIBUTION_COUNT = 2
    }
}
