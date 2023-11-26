package blackjack

import blackjack.model.BlackJackGame
import blackjack.model.pack.impl.ShuffledPack
import blackjack.view.InputView
import blackjack.view.OutputView

fun main() {
    val participants = InputView.join()
    participants.dealing(ShuffledPack)
    OutputView.dealing(participants)
    OutputView.presentCards(participants)
    BlackJackGame(participants).start()
    OutputView.presentScores(participants)
    OutputView.presentResult(participants)
}
