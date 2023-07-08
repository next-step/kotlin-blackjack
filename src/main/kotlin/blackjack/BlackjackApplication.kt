package blackjack

import blackjack.controller.BlackjackController

fun main() {
    val blackjackController = BlackjackController()
    val players = blackjackController.inputPlayers()

    blackjackController.drawInitialCards(players)
    println()
    blackjackController.printInitialCards(players)
    println()

    for (player in players) {
        while (blackjackController.drawMoreCard(player)) {
            continue
        }
    }
    println()

    // 결과를 출력한다.
    blackjackController.printResult(players)
}
