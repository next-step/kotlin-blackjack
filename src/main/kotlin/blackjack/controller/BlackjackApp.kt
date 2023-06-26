package blackjack.controller

import blackjack.domain.BlackjackGame
import blackjack.domain.Deck
import blackjack.view.InputView
import blackjack.view.OutputView

fun main() {
    val inputUsers = InputView.inputPlayers()
    val printFirstDeal = OutputView.printFirstDeal(inputUsers)

    val deck = Deck.make()
    val game = BlackjackGame(inputUsers)
    game.getPlayers().forEach {
        OutputView.printPlayerCards(it)
    }
}
