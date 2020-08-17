package blackjack

import blackjack.domain.Deck
import blackjack.domain.Players
import blackjack.view.InputView
import blackjack.view.ResultView

fun main() {
    val players = InputView.getPlayers()
    players.addCards(Deck, 2)
    ResultView.printPlayersCard(players)
    playBlackjack(players)
    ResultView.printResult(players)
}

fun playBlackjack(players: Players) {
    players.players.forEach {
        while (it.isGaming()) {
            val yOrN = InputView.getMoreCard(it)
            it.choose(yOrN)
        }
        ResultView.printPlayerCard(it)
    }
}
