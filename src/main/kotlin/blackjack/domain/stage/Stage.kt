package blackjack.domain.stage

import blackjack.domain.BlackJackGame

interface Stage {
    fun dealCards(game: BlackJackGame)

    fun emitResult(game: BlackJackGame)

    fun nextStage(game: BlackJackGame): Stage
}
