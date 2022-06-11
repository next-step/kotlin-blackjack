package blackjack

import blackjack.controller.BlackJackGame
import blackjack.model.DefaultCardDistributor
import blackjack.view.input.ConsolePlayerProvider
import blackjack.view.input.ConsoleReader
import blackjack.view.input.parser.YesNoInputParser
import blackjack.view.output.ConsoleOutputView

class BlackJackApplication {

    fun run() {
        val blackJackGame = createGame()
        do {
            blackJackGame.run()
        } while (ConsoleReader.read("한번 더 하시겠습니까?", YesNoInputParser()))
    }

    private fun createGame() = BlackJackGame(
        playerProvider = ConsolePlayerProvider(),
        cardDistributor = DefaultCardDistributor(),
        outputView = ConsoleOutputView()
    )
}

fun main() {
    BlackJackApplication().run()
}
