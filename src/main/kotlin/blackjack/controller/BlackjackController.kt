package blackjack.controller

import blackjack.domain.Game
import blackjack.domain.Player
import blackjack.view.InputView
import blackjack.view.ResultView

class BlackjackController {
    fun play() {
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
    BlackjackController().play()
}
