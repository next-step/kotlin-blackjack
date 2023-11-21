package blackjack.domain.stage

import blackjack.domain.BlackJackGame

class EndStage : Stage {
    override fun dealCards(game: BlackJackGame) {}

    override fun emitResult(game: BlackJackGame) {}

    override fun nextStage(game: BlackJackGame): Stage = this
}
