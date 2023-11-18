package blackjack.domain.stage

import blackjack.domain.BlackJackGame

class InGame(
    private val game: BlackJackGame,
) : Stage {
    private val playerChoice by lazy {
        game.askHitOrStand()
    }

    override fun progress() {
        playerChoice
        TODO("Not yet implemented")
    }

    override fun emitResult() {
        TODO("Not yet implemented")
    }

    override fun nextStage(): Stage {
        TODO("Not yet implemented")
    }
}
