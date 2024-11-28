package blackjack.controller

import blackjack.service.BlackJackService
import blackjack.view.InputView
import blackjack.view.ResultView

object BlackjackController {
    private val blackJackService = BlackJackService()

    fun start() {
        val playersInput = InputView.inputName()
        val players = blackJackService.splitInput(playersInput)
        ResultView.printSplitCardResult(players[0], players[1])
    }
}