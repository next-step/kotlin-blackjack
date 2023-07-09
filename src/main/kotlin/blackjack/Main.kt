package blackjack

import blackjack.domain.BlackjackGame
import blackjack.domain.Challenger
import blackjack.domain.Challengers
import blackjack.view.InputView
import blackjack.view.ResultView

fun main() {
    val inputView = InputView()
    val resultView = ResultView()

    val challengers = inputView.inputPlayerNames()
        .map { Challenger(it) }
        .let { Challengers(it) }
    val blackjackGame = BlackjackGame(challengers)

    blackjackGame.dealInitialHand()
    resultView.outputInitialHand(blackjackGame.challengers, blackjackGame.dealer)

    blackjackGame.challengers.forEach { challenger ->
        while (challenger.canHit) {
            val hit = inputView.inputHitDecision(challenger)
            if (hit) {
                blackjackGame.dealCardTo(challenger)
                resultView.outputCurrentHand(challenger)
            } else {
                challenger.stay()
            }
        }
    }
    while (blackjackGame.dealer.canHit) {
        blackjackGame.dealCardTo(blackjackGame.dealer)
        resultView.outputDealerDeal()
    }

    resultView.outputGameResult(blackjackGame.challengers, blackjackGame.dealer)
}
