package blackjack.controller

import blackjack.view.InputView

fun main() {
    val players = InputView.readPlayers()

    players.forEach { println(it) }
}