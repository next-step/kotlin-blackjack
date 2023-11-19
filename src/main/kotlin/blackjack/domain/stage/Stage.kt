package blackjack.domain.stage

import blackjack.domain.BlackJackGame

interface Stage {
    fun progress(game: BlackJackGame)

    fun emitResult(game: BlackJackGame)

    fun nextStage(game: BlackJackGame): Stage
}
