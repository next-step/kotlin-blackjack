package blackjack.controller

import blackjack.domain.Deck
import blackjack.domain.Game
import blackjack.domain.Player
import blackjack.domain.Players
import blackjack.view.ConsoleInput
import blackjack.view.ConsoleOutput

class BlackjackController {
    private val inputView = ConsoleInput
    private val outputView = ConsoleOutput

    fun playGame() {
        val game = Game(inputView.inputPlayers())
        val players = game.initialCard()
        outputView.printInitialCards(players)
        val result = scratchPlayers(players, game.deck)
        outputView.printGameResult(result)
    }

    private fun scratchPlayers(players: Players, deck: Deck): Players {
        val result = mutableListOf<Player>()
        players.list.map {
            var player = it
            while (!player.isBurst() && ConsoleInput.inputScratch(player)) {
                player = player.hit(deck.draw())
                ConsoleOutput.printPlayerCards(player)
            }
            result.add(player)
        }

        outputView.printLine()
        return Players(result)
    }
}

fun main() {
    BlackjackController().playGame()
}
