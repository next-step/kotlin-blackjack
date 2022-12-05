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
        while (InputView.isDrawMoreCard(player)) {
            player.addCard(deck.draw())
            ResultView.printCardScoreDescription(player)
        }
    }

    players.forEach { ResultView.printResults(it) }
}
