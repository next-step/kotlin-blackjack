package blackjack.controller

import blackjack.domain.BlackjackGame
import blackjack.domain.Challenger
import blackjack.domain.Challengers
import blackjack.view.InputView
import blackjack.view.ResultView

class BlackjackController(
    private val inputView: InputView = InputView(),
    private val resultView: ResultView = ResultView(),
) {
    fun play() {
        val challengers = inputView.inputPlayerNames()
            .map { Challenger(it) }
            .let { Challengers(it) }
        val blackjackGame = BlackjackGame(challengers)

        start(blackjackGame)
        deal(blackjackGame)
        end(blackjackGame)
    }

    private fun start(blackjackGame: BlackjackGame) {
        blackjackGame.dealInitialHand()
        resultView.outputInitialHand(blackjackGame.challengers, blackjackGame.dealer)
    }

    private fun deal(blackjackGame: BlackjackGame) {
        dealToChallenger(blackjackGame)
        dealToDealer(blackjackGame)
    }

    private fun dealToDealer(blackjackGame: BlackjackGame) {
        while (blackjackGame.dealer.canHit) {
            blackjackGame.dealCardTo(blackjackGame.dealer)
            resultView.outputDealerDeal()
        }
    }

    private fun dealToChallenger(blackjackGame: BlackjackGame) {
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
    }

    private fun end(blackjackGame: BlackjackGame) {
        resultView.outputGameResult(blackjackGame.challengers, blackjackGame.dealer)
    }
}
