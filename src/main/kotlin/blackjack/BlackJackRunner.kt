package blackjack

import blackjack.controller.InputProcessor
import blackjack.domain.BlackJackGame

object BlackJackRunner {
    fun start() {
        val game = BlackJackGame(InputProcessor.playerNames)
        game.run()
    }
}

fun main() {
    BlackJackRunner.start()
}
