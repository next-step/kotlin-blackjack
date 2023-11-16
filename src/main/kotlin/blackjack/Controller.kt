package blackjack

import blackjack.view.InputView
import blackjack.view.OutputView

fun main() {
    val participants = InputView.join()
    participants.dealing()
    OutputView.dealing(participants)
    OutputView.presentCards(participants)
    while (!participants.isContinue()) {

        InputView.draw(participants)
    }

    OutputView.result(participants)
}
