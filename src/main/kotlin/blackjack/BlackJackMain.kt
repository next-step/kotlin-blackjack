package blackjack

import blackjack.domain.Dealer
import blackjack.domain.Deck
import blackjack.domain.GameResult
import blackjack.domain.Player
import blackjack.view.InputView
import blackjack.view.ResultView

fun main() {
    val deck = Deck.init()
    val players = InputView.getPlayers()
    val dealer = Dealer()

    ResultView.firstDealCard(players, dealer)

    dealer.getFirstDealCards(deck.firstDraw())
    ResultView.showPlayerCards(dealer)

    for (player in players) {
        player.getFirstDealCards(deck.firstDraw())
        ResultView.showPlayerCards(player)
    }

    for (player in players) {
        player.isHit(deck)
    }

    ResultView.showDealerDrawCount(dealer.getCard(deck))
    ResultView.showPlayerResult(dealer, players)

    val gameResult = GameResult(dealer, players)
    ResultView.displayGameResult(gameResult)
}

private fun Player.isHit(deck: Deck) {
    while (InputView.askPlayer(this.name) && !isBusted) {
        hit(deck.draw())
        ResultView.showPlayerCards(this)
    }
}
