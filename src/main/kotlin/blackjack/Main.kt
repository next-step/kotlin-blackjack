package blackjack

import blackjack.domain.Player
import blackjack.view.InputView

fun main() {
    val playerNames = InputView.receivePlayerNames()
    val players = playerNames.map { Player(it) }
}
