package blackjack

import blackjack.domain.BlackjackGame
import blackjack.domain.Dealer
import blackjack.domain.PlayerState
import blackjack.view.InputView
import blackjack.view.OutputView

fun main() {
    val dealer = Dealer()
    val players = InputView.inputPlayers()
    val blackJackGame = BlackjackGame(dealer, players)

    OutputView.printInitGame(blackJackGame)
    blackJackGame.start()
}
