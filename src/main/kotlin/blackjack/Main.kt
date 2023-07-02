package blackjack

import blackjack.game.BlackjackGame
import blackjack.view.InputView
import blackjack.view.ResultView

fun main() {
    val blackjackGame = BlackjackGame(InputView, ResultView)
    blackjackGame.start()
}
