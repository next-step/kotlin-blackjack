package blackjack.controller

import blackjack.domain.Game
import blackjack.domain.Player
import blackjack.view.InputView
import blackjack.view.ResultView

class BlackjackController {
    fun run() {
        setPlayers()
        while (true) {
            start()
            if (Game.isFinished()) break
        }
        ResultView.printResult()
    }

    private fun start() {
        Game.players.forEach {
            drawOrNot(it)
        }
    }

    private fun drawOrNot(player: Player) {
        while (true) {
            if (!InputView.inputIsGetCard(player)) break
            Game.hit(player)
            ResultView.printPlayerStatus(player)
        }
    }

    private fun setPlayers() {
        addPlayers(InputView.inputPlayersName())
        ResultView.printInitialStatus()
    }

    private fun addPlayers(names: List<String>) {
        val players = names.map { Player(it) }
        Game.players.addAll(players)
    }
}

fun main() {
    BlackjackController().run()
}
