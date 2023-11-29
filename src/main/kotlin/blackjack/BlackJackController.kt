package blackjack

import blackjack.domain.Deck
import blackjack.domain.Player
import blackjack.view.InputView
import blackjack.view.OutputView

fun main() {
    val deck = Deck.create()
    val players = InputView.inputNames().map { Player(it) }

    players.forEach() {
        it.addCard(deck.draw(2))
    }
    OutputView.printDrawTwoCards(players)
}
