package controller

import domain.Game
import domain.Player
import presentation.InputView

fun main() {
    Game(
        InputView.getPlayerNames()
            .map { Player(it) }
    )
}
