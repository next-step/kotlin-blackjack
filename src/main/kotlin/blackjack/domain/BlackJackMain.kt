package blackjack.domain

import blackjack.view.InputView
import blackjack.view.ResultView

fun main() {
    val deck = Deck.init()
    val players = InputView.getPlayers()
    val dealer = Dealer()

    ResultView.firstDealCard(players, dealer)

    dealer.getFirstTwoCards(deck.firstDraw())
    ResultView.showPlayerCards(dealer)

    for (player in players) {
        player.getFirstTwoCards(deck.firstDraw())
        ResultView.showPlayerCards(player)
    }

    for (player in players) {
        player.isHit(deck)
    }

    dealer.getCard(deck)
    ResultView.showPlayerResult(dealer, players)

    val gameResult = GameResult(dealer, players)
    ResultView.displayGameResult(gameResult)
}

private fun Dealer.getCard(deck: Deck) {
    var count = 0
    while (canHit) {
        hit(deck.draw())
        count++
    }
    ResultView.showDealerDrawCount(count)
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
