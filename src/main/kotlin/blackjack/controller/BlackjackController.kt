package blackjack.controller

import blackjack.domain.Game
import blackjack.domain.Player
import blackjack.view.InputView
import blackjack.view.ResultView

class BlackjackController {
    fun run() {
        setPlayers()
        play()
        ResultView.printResult()
    }

    private fun play() {
        Game.players.forEach {
            if (it.isBust()) return@forEach
            drawOrNot(it)
        }
    }

    private fun drawOrNot(player: Player) {
        while (InputView.inputIsGetCard(player)) {
            Game.hit(player)
            ResultView.printPlayerStatus(player)
            if (player.isBust()) break
        }
    }

    private fun setPlayers() {
        val names = InputView.inputPlayersName()
        val players = names.map { Player(it) }
        Game.players.addAll(players)
        ResultView.printInitialStatus()
    }
}

fun main() {
    BlackjackController().run()
}
