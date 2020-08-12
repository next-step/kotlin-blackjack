package blackjack

import blackjack.model.BlackJackGame
import blackjack.model.Players
import blackjack.model.card.CardDeck
import blackjack.view.InputView
import blackjack.view.ResultView

object BlackJackGameController {
    private val inputView = InputView
    private val resultView = ResultView
    private val blackJackGame =
        BlackJackGame(Players(inputView.getNames()), CardDeck())

    fun startGame() {
        blackJackGame.gameRun(
            isHit = (InputView::oneMore),
            showPlayer = (ResultView::showPlayer)
        ).also {
            resultView.showResult(it)
        }
    }
}

fun main() {
    BlackJackGameController.startGame()
}
