package blackjack

import blackjack.controller.ResultProcessor
import blackjack.controller.ViewInputProcessor
import blackjack.domain.BlackJackGame
import blackjack.domain.stage.EndStage

class BlackJackRunner(
    private val game: BlackJackGame = BlackJackGame(
        inputProcessor = ViewInputProcessor(),
        resultProcessor = ResultProcessor(),
    ),
) {
    fun start() {
        var stageCount = 0
        while (game.stage !is EndStage || stageCount < MAX_STAGE) {
            game.run()
            stageCount++
        }
    }

    companion object {
        private const val MAX_STAGE = 20
    }
}
