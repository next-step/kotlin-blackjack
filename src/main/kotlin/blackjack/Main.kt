package blackjack

import blackjack.controller.BlackJackController
import blackjack.service.BlackjackService
import blackjack.view.InputView
import blackjack.view.ResultView

fun main() {
    BlackJackController(BlackjackService(), InputView, ResultView).play()
}
