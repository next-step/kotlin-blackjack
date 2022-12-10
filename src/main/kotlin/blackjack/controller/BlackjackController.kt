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
            draw(it)
        }
    }

    private fun draw(player: Player) {
        while (true) {
            if (!InputView.inputIsGetCard(player)) break
            Game.givePlayer(player)
            ResultView.printPlayerStatus(player)
        }
    }

    private fun setPlayers() {
        addPlayers(InputView.inputPlayersName())
        ResultView.printInitialStatus()
    }

    private fun addPlayers(names: List<String>) {
        val players = names.map { Player(it) }
        players.forEach {
            Game.addPlayer(it)
        }
    }
}

fun main() {
    BlackjackController().run()
}
