package blackjack.domain.stage

import blackjack.domain.BlackJackGame

class InitialDistribution(
    private val game: BlackJackGame,
) : Stage {
    override fun receiveSetupData() {}

    override fun progress() {
        game.dealCardsToAllPlayers(INITIAL_DISTRIBUTION_COUNT)
    }

    override fun handleResult() {
        TODO("Not yet implemented")
    }

    override fun nextStage(): Stage {
        TODO("Not yet implemented")
    }

    companion object {
        private const val INITIAL_DISTRIBUTION_COUNT = 2
    }
}
