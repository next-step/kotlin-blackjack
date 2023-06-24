package blackjack.controller

import blackjack.domain.BlackjackGame
import blackjack.view.InputView

fun main() {
    val inputUsers = InputView.inputPlayers()
    BlackjackGame(inputUsers)
}
