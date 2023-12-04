package blackjack

import blackjack.domain.Dealer
import blackjack.domain.Deck
import blackjack.domain.Players
import blackjack.view.InputView
import blackjack.view.ResultView

fun main() {
    val deck = Deck.init()
    val players = InputView.getPlayers()
    for (player in players) {
        InputView.getPlayersBettingAmount(player)
    }
    val dealer = Dealer()
    val playersObject = Players(players)

    ResultView.firstDealCard(players, dealer)

    dealer.getFirstDealCards(deck.firstDraw())
    ResultView.showPlayerCards(dealer)

    playersObject.getFirstTwoCards(deck) { player ->
        ResultView.showPlayerCards(player)
    }

    for (player in players) {
        while (!player.isBusted && InputView.askPlayer(player.name)) {
            player.hit(deck.draw())
            ResultView.showPlayerCards(player)
        }
    }

    ResultView.showPlayerResult(dealer.getCard(deck), dealer, players)
}
