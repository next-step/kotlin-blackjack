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
        val result = game.play(players, ::isHit, ::printCard)
        endGame(result)
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

    private fun isHit(player: Player) = InputView.isHit(player.name)

    private fun printCard(player: Player) = OutputView.printPlayerCard(player)

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
