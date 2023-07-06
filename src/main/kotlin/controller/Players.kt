package controller

import domain.Game
import domain.Player
import presentation.InputView
import presentation.ResultView

class Players(private val players: List<Player>) {
    fun receiveMoreCard(game: Game) {
        players.forEach {
            it.receiveMoreCard(game)
        }
    }

    fun noMorePlayer(): Boolean {
        return players.isEmpty()
    }

    private fun Player.receiveMoreCard(game: Game) {
        if (InputView.askReceiveCard(name)) {
            game.dealAdditionalCard(this)
            ResultView.printPlayerState(this)
        }
    }
}
