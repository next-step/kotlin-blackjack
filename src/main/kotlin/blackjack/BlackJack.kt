package blackjack

import blackjack.ui.BlackjackView.inputCard
import blackjack.ui.BlackjackView.inputGamers
import blackjack.ui.BlackjackView.printResult
import blackjack.ui.BlackjackView.printStartGamer

fun main() {
    var gamers = inputGamers()

    printStartGamer(gamers)

    while (true) {
        gamers = inputCard(gamers)

        if (gamers.isFinish()) {
            break
        }
    }

    printResult(gamers)
}
