package blackjack.application

import blackjack.presentation.InputView
import blackjack.presentation.ResultView

object BlackJackController {
    fun start() {
        val names = InputView.getNames()
        val players = BlackJackService.start(names)
        ResultView.printResult(players)
    }
}
