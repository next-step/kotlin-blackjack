package blackjack.domain.stage

import blackjack.domain.BlackJackGame

class EndStage(
    private val game: BlackJackGame,
) : Stage {
    override fun progress() {}

    override fun emitResult() {}

    override fun nextStage(): Stage = this
}
