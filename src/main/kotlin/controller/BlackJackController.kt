package controller

import domain.Game
import domain.Player
import presentation.InputView

fun main() {
    val game = Game(
        InputView.getPlayerNames()
            .map { Player(it) }
    )
    game.start()
}
