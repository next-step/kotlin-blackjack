package blackjack

import blackjack.domain.RandomDeck
import blackjack.ui.InputView
import blackjack.ui.ResultView

fun main() {
    val players = InputView.inputNames()

    val deck = RandomDeck.from()
    val initPlayers = players.initCard(deck)
    ResultView.printInitPlayers(initPlayers)
}
