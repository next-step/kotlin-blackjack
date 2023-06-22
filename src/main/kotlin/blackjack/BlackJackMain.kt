package blackjack

import blackjack.view.InputView

fun main() {
    val playerNames = InputView.getPlayers()
    val players = playerNames.map { Player(it) }
}
