package blackjack.controller

import blackjack.domain.BlackjackGame
import blackjack.domain.Deck
import blackjack.domain.RandomCardStrategy
import blackjack.view.InputView
import blackjack.view.OutputView

fun main() {
    BlackjackGame(InputView(), OutputView(), Deck(RandomCardStrategy())).run {
        start()
        hitOrStay()
        showResult()
    }
}
