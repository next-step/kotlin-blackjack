package blackjack

import blackjack.controller.BlackjackController

fun main() {
    val blackjackController = BlackjackController()
    val players = blackjackController.inputPlayers()

    blackjackController.drawInitialCards(players)
    println()
    blackjackController.printInitialCards(players)
}
