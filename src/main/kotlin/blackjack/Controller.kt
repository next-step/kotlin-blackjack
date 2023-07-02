package blackjack

import blackjack.domain.BlackJackGame
import blackjack.domain.Player
import blackjack.view.InputView
import blackjack.view.OutputView

class Controller {
    fun playBlackJackGame() {
        val game = BlackJackGame.create()
        val players = startGame(game, getPlayers())
        val result = players.map { playGame(game, it) }
        endGame(result)
    }

    private fun getPlayers(): List<Player> {
        val playerNames = InputView.getPlayerNames()
        return playerNames.map { Player.init(it) }
    }

    private fun startGame(game: BlackJackGame, players: List<Player>): List<Player> {
        val result = game.start(players)
        OutputView.printStart(result)
        return result
    }

    private fun playGame(game: BlackJackGame, player: Player): Player {
        var result = player
        while (result.canAddCard()) {
            result = playRound(game, result)
        }
        return result
    }

    private fun playRound(game: BlackJackGame, player: Player): Player {
        if (InputView.askForAddCard(player.name).not()) return player
        return game.addCard(player).also {
            OutputView.printPlayerCard(it)
        }
    }

    private fun endGame(players: List<Player>) {
        for (player in players) {
            OutputView.printPlayerResult(player)
        }
    }
}

fun main() {
    val controller = Controller()
    controller.playBlackJackGame()
}
