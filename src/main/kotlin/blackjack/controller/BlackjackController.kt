package blackjack.controller

import blackjack.domain.BlackjackGame
import blackjack.domain.Challenger
import blackjack.domain.OnAfterChallengerHit
import blackjack.domain.OnAfterDealerHit
import blackjack.domain.OnAfterInitializeHands
import blackjack.domain.OnHitDecided
import blackjack.domain.player.Challengers
import blackjack.view.InputView
import blackjack.view.ResultView

class BlackjackController(
    private val inputView: InputView = InputView(),
    private val resultView: ResultView = ResultView(),
) {
    fun play() {
        val challengers = Challengers(
            inputView.inputPlayerNames().map { Challenger(it, bettingAmount = inputView.inputBettingAmount(it)) }
        )
        val onHitDecided: OnHitDecided = { inputView.inputHitDecision(it) }
        val onAfterInitializeHands: OnAfterInitializeHands = { challengers, dealer ->
            resultView.outputInitialHand(challengers, dealer)
        }
        val onAfterChallengerHit: OnAfterChallengerHit = { resultView.outputCurrentHand(it) }
        val onAfterDealerHit: OnAfterDealerHit = { resultView.outputDealerDeal() }

        val blackjackGame = BlackjackGame(
            challengers = challengers,
            onHitDecided = onHitDecided,
            onAfterInitializeHands = onAfterInitializeHands,
            onAfterChallengerHit = onAfterChallengerHit,
            onAfterDealerHit = onAfterDealerHit,
        )

        blackjackGame.run()
        end(blackjackGame)
    }

    private fun end(blackjackGame: BlackjackGame) {
        resultView.outputGameResult(blackjackGame.challengers, blackjackGame.dealer)
    }
}
