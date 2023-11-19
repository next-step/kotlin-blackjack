package blackjack.domain.stage

import blackjack.domain.BlackJackGame
import blackjack.domain.result.DetermineWinnerResult

class DetermineWinnerStage(
    private val game: BlackJackGame,
) : Stage {
    override fun progress() {}

    override fun emitResult() {
        game.emitResult(DetermineWinnerResult(game.players))
    }

    override fun nextStage(): Stage = EndStage(game)
}
