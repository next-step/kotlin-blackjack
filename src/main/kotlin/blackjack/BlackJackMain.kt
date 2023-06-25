package blackjack

import blackjack.view.InputView
import blackjack.view.OutputView

fun main() {
    val playerNames = InputView.getPlayers()
    val players = playerNames.map { Player(it) }
    val dealer = Dealer()
    val initialCastingCardNum = 2
    dealer.provideCard(players, initialCastingCardNum)
    OutputView.initialCardCasting(players, initialCastingCardNum)
}
