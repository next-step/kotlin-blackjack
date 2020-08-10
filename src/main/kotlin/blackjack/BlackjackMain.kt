package blackjack

import blackjack.model.BlackJackGame
import blackjack.view.InputView
import blackjack.view.OutputView

fun main() {
    val players = InputView.getPlayer()
    val blackJackGame = BlackJackGame(players)

    blackJackGame.firstTurn()
    blackJackGame.progressTurn()

    OutputView.printPoint(players)
}
