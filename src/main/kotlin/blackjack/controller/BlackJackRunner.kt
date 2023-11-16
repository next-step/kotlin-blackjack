package blackjack.controller

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
