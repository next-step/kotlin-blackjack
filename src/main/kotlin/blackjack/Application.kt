package blackjack

import blackjack.controller.Controller

fun main() {

    val controller = Controller()

    val gamePlayUsers = controller.gameStart()

    gamePlayUsers.playUsers.forEach { controller.playing(it) }

    controller.gameEnd(gamePlayUsers)
}
