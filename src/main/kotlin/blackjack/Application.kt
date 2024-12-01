package blackjack

import blackjack.domain.Game
import blackjack.domain.Player
import blackjack.ui.InputView
import blackjack.ui.ResultView

fun main() {
    val inputView = InputView()
    val resultView = ResultView()
    val players = inputView.getPlayers().map { Player(it) }

    val game = Game.createGame(players)
    val printCallback: ((List<Player>) -> Unit) = {
        it.forEach { player ->
            resultView.printPlayerResult(player)
        }
    }
    val turnCallback: ((Player) -> String) = { player ->
        inputView.setUserAnswer(player.name)
        inputView.getUserAnswer()
    }

    resultView.printStartMessage(game.players)
    game.startGame(printCallback, turnCallback)
    resultView.printGameResult(game.players)
}
