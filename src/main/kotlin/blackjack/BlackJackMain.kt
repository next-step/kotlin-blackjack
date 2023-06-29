package blackjack

import blackjack.card.deck.BlackJackCardDeck
import blackjack.card.score.BlackJackScoringStrategy
import blackjack.view.StdInInputView
import blackjack.view.StdOutOutputView

fun main() {
    BlackjackGame(Dealer(BlackJackCardDeck()), BlackJackScoringStrategy(), StdInInputView, StdOutOutputView).start()
}
