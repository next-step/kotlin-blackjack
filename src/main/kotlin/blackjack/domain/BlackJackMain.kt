package blackjack.domain

import blackjack.view.InputView
import blackjack.view.ResultView

fun main() {
    val deck = Deck.init()
    val players = InputView.getPlayers()

    ResultView.firstDealCard(players)

    for (player in players) {
        player.getFirstTwoCards(deck.firstDraw())
        ResultView.showPlayerCards(player)
    }

    for (player in players) {
        player.isHit(deck)
    }

    ResultView.showPlayerResult(players)
}

private fun Player.isHit(deck: Deck) {
    var sayHit = InputView.askPlayer(this.name)
    while (sayHit) {
        hit(deck.draw())
        ResultView.showPlayerCards(this)
        if (this.isBusted) break
        sayHit = InputView.askPlayer(this.name)
    }
}
