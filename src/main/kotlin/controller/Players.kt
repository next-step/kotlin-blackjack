package controller

import domain.Game
import domain.Player
import presentation.InputView
import presentation.ResultView

class Players(private val players: List<Player>) {
    fun hit(game: Game) {
        players.forEach { player ->
            hitFor(player, game)
        }
    }

    fun noMorePlayer(): Boolean {
        return players.isEmpty()
    }

    private fun hitFor(player: Player, game: Game) {
        if (!InputView.askReceiveCard(player.name)) return
        game.dealAdditionalCard(player)
        ResultView.printPlayerState(player)
    }
}
