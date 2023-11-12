package blackjack

import blackjack.business.Cards
import blackjack.business.PlayerNameParser
import blackjack.business.Players
import blackjack.view.InputHandler
import blackjack.view.OutputHandler

object GameManager {
    fun play() {
        val playerNames = InputHandler.askForPlayerNames()
        val players = Players.from(PlayerNameParser.parse(playerNames))
        val cards = Cards()
        players.players.forEach { player ->
            player.addCard(cards.draw())
            player.addCard(cards.draw())
        }
        players.players.forEach { player ->
            OutputHandler.printPlayer(player)
        }
        players.players.forEach { player ->
            while (player.canDrawCard() && InputHandler.askForOneMore(player.name) == "y") {
                player.addCard(cards.draw())
                OutputHandler.printPlayer(player)
            }
        }
        println()
        players.players.forEach { player ->
            OutputHandler.printResult(player)
        }
    }
}

fun main() {
    GameManager.play()
}
