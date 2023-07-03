package blackjack

import blackjack.domain.BlackJackGame
import blackjack.domain.Player
import blackjack.domain.Players
import blackjack.view.InputView
import blackjack.view.OutputView

class Controller {
    fun playBlackJackGame() {
        val game = BlackJackGame.create()
        val players = startGame(game, getPlayers())
        val result = players.map { playGame(game, it) }
        endGame(Players(result))
    }

    private fun getPlayers(): Players {
        val playerNames = InputView.getPlayerNames()
        return Players.of(playerNames)
    }

    private fun startGame(game: BlackJackGame, players: Players): Players {
        val result = game.start(players)
        OutputView.printStart(result)
        return result
    }

    private fun playGame(game: BlackJackGame, player: Player): Player {
        var result = player
        while (result.canDraw()) {
            result = playRound(game, result)
        }
        return result
    }

    private fun playRound(game: BlackJackGame, player: Player): Player {
        if (InputView.askForContinue(player.name).not()) return player
        return game.addCard(player).also {
            OutputView.printPlayerCard(it)
        }
    }

    private fun endGame(players: Players) {
        for (player in players.values) {
            OutputView.printPlayerResult(player)
        }
    }
}

fun main() {
    val controller = Controller()
    controller.playBlackJackGame()
}
