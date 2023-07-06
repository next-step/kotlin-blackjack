package blackjack

import blackjack.domain.BlackJackGame
import blackjack.domain.Dealer
import blackjack.view.InputView
import blackjack.view.OutputView

fun main() {
    val dealer = Dealer()
    val players = InputView.inputPlayers()
    val blackJackGame = BlackJackGame(dealer, players)

    OutputView.printInitGame(blackJackGame)
}
