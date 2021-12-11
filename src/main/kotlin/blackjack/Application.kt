package blackjack

import blackjack.play.Controller

fun main() {
    val gamePlayUsers = Controller.gameStart()
    gamePlayUsers.playing()
    Controller.gameEnd(gamePlayUsers)
}
