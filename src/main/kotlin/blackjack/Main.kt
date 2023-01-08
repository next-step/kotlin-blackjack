package blackjack

import blackjack.controller.Casino
import blackjack.domain.Gamer
import blackjack.ui.InputView
import blackjack.ui.ResultView

fun main() {
    val casino = Casino(InputView(), ResultView())
    casino.prepare()
    casino.drawTwoCards()
    casino.showPlayers()
    casino.relay()
    casino.showResult(casino)
    casino.showReport(casino)
}
