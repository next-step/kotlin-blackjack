package blackjack

import blackjack.controller.Casino
import blackjack.domain.Dealer
import blackjack.ui.InputView
import blackjack.ui.ResultView

fun main() {
    val casino = Casino(Dealer(), InputView(), ResultView())
    casino.drawTwoCards()
    casino.showPlayers()
    casino.relay()
    casino.showResult(casino)
    casino.showReport(casino)
}
