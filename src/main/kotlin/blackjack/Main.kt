package blackjack

import blackjack.controller.BlackjackController

fun main() {
    val controller = BlackjackController()
    val game = controller.startGame()
    repeat(game.players.values.size) { controller.progressPlayerPhase(game) }
    controller.progressEndPhase(game)
}
