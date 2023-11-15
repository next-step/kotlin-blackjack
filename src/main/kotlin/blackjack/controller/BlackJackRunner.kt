package blackjack.controller

import blackjack.domain.BlackJackGame

object BlackJackRunner {
    fun start() {
        val game = BlackJackGame(InputProcessor.playerNames)
    }
}

fun main() {
    BlackJackRunner.start()
}
