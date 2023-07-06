package blackjack

import blackjack.controller.BlackjackController

fun main() {
    val blackjackController = BlackjackController()
    val players = blackjackController.inputPlayers()

    blackjackController.drawInitialCards(players)
    println()
    blackjackController.printInitialCards(players)

    var playerIterator = players.iterator()
    var currentPlayer = playerIterator.next()
    val isPlaying = true
    while (isPlaying) {
        if (blackjackController.drawMoreCard(currentPlayer)) {
            continue
        }
        if (playerIterator.hasNext()) {
            currentPlayer = playerIterator.next()
            continue
        }
        playerIterator = players.iterator()
    }
}
