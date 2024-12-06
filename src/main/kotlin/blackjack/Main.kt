package blackjack

import blackjack.view.InputView
import blackjack.view.ResultView

fun main() {
    val inputView = InputView()
    val blackJackGame = inputView.inputBlackJack()
    val resultView = ResultView()
    resultView.printBlackJackGameResult(blackJackGame.result())
}
