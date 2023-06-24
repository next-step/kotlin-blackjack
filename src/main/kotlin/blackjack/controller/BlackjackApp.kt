package blackjack.controller

import blackjack.domain.BlackjackGame
import blackjack.view.InputView
import blackjack.view.OutputView

fun main() {
    val inputUsers = InputView.inputPlayers()
    val game = BlackjackGame(inputUsers)
    val printFirstDeal = OutputView.printFirstDeal(game.getPlayers())
}
