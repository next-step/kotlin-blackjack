package blackjack

import blackjack.controller.Controller

fun main() {
    val gamePlayUsers = Controller.gameStart()
    gamePlayUsers.playing()
    Controller.gameEnd(gamePlayUsers)
}
