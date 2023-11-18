package blackjack

import blackjack.controller.ResultProcessor
import blackjack.controller.ViewInputProcessor
import blackjack.domain.BlackJackGame

object BlackJackRunner {
    fun start() {
        val inputProcessor = ViewInputProcessor()

        val game = BlackJackGame(
            playerNames = inputProcessor.playerNames(),
            inputProcessor = inputProcessor,
            resultProcessor = ResultProcessor(),
        )
        game.run()
    }
}

fun main() {
    BlackJackRunner.start()
}
