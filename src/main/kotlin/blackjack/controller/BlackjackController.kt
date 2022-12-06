package blackjack.controller

import blackjack.domain.Game
import blackjack.view.ConsoleInput
import blackjack.view.ConsoleOutput

class BlackjackController {
    fun playGame() {
        val players = ConsoleInput().inputPlayers()
        val game = Game(players)
        ConsoleOutput().printInitialCards(game.players)

        game.play()
        ConsoleOutput().printGameResult(game)
    }
}

fun main() {
    BlackjackController().playGame()
}
