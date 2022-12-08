package blackjack

import blackjack.domain.Deck
import blackjack.domain.Player
import blackjack.domain.Players
import blackjack.view.InputView
import blackjack.view.ResultView

fun main() {
    val usersNames = InputView.inputUsersNames()
    val deck = Deck.init()

    val players = Players.init(usersNames, deck)
    ResultView.printDrawResults(players)

    for (player in players.items) {
        player.drawCardUntilWant(deck)
    }

    players.items.forEach { ResultView.printResults(it) }
}

private fun Player.drawCardUntilWant(deck: Deck) {
    while (this.ableMoreDrawCard() && InputView.checkWantDrawMoreCard(this)) {
        this.addCard(deck.draw())
        ResultView.printCardScoreDescription(this)
    }
}
