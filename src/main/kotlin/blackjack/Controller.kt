package blackjack

import blackjack.view.InputView
import blackjack.view.OutputView

fun main() {
    val ps = InputView.join()
    OutputView.presentCards(ps)
    InputView.draw(ps)
    OutputView.result(ps)
}
