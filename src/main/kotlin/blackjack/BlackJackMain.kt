package blackjack

import blackjack.card.score.BlackJackScoringStrategy
import blackjack.view.StdInInputView
import blackjack.view.StdOutOutputView

fun main() {
    BlackjackGame(Dealer(), BlackJackScoringStrategy(), StdInInputView, StdOutOutputView).start()
}
