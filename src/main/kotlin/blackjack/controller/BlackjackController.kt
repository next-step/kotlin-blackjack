package blackjack.controller

import blackjack.domain.Game
import blackjack.view.ConsoleInput
import blackjack.view.ConsoleOutput

class BlackjackController {
    private val inputView = ConsoleInput
    private val outputView = ConsoleOutput

    fun playGame() {
        val players = inputView.inputPlayers()
        val game = Game(players)
        outputView.printInitialCards(game.players)
        scratchPlayers(game)
        outputView.printGameResult(game.players)
    }

    private fun scratchPlayers(game: Game) {
        game.getPlayers().forEach {
            while (!it.isBurst() && ConsoleInput.inputScratch(it)) {
                it.hit(game.dealer.divide())
                ConsoleOutput.printPlayerCards(it)
            }
        }
        ConsoleOutput.printLine()
    }
}

fun main() {
    BlackjackController().playGame()
}
