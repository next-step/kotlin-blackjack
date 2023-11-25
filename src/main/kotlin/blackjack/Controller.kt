package blackjack

import blackjack.model.BlackJackGame
import blackjack.model.pack.impl.ShuffledPack
import blackjack.model.result.DealerResult
import blackjack.view.InputView
import blackjack.view.OutputView

fun main() {
    val participants = InputView.join()
    participants.dealing(ShuffledPack)
    OutputView.dealing(participants)
    OutputView.presentCards(participants)
    val game = BlackJackGame(participants)
    game.start()
    OutputView.presentScores(participants)
    OutputView.presentResult(DealerResult.a())
}
