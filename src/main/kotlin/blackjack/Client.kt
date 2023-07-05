package blackjack

import blackjack.domain.Dealer
import blackjack.view.InputView

fun main() {
    val dealer = Dealer()
    val players = InputView.inputPlayers()
}
