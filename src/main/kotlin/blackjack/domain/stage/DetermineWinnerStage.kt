package blackjack.domain.stage

import blackjack.domain.BlackJackGame
import blackjack.domain.result.DetermineWinnerResult

class DetermineWinnerStage: Stage {
    override fun progress(game: BlackJackGame) {}

    override fun emitResult(game: BlackJackGame) {
        game.emitResult(DetermineWinnerResult(game.players))
    }

    override fun nextStage(game: BlackJackGame): Stage = EndStage()
}
