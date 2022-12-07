package blackjack

import blackjack.domain.Deck
import blackjack.domain.Player
import blackjack.view.InputView
import blackjack.view.ResultView

fun main() {
    val usersNames = InputView.inputUsersNames()
    val deck = Deck.init()

    val players = usersNames.map { name ->
        Player.init(name, deck)
    }

    ResultView.printDrawResults(players)

    for (player in players) {
        player.drawCardUntilWant(deck)
    }

    players.forEach { ResultView.printResults(it) }
}

private fun Player.drawCardUntilWant(deck: Deck) {
    while (this.ableMoreDrawCard() && InputView.checkWantDrawMoreCard(this)) {
        this.addCard(deck.draw())
        ResultView.printCardScoreDescription(this)
    }
}
