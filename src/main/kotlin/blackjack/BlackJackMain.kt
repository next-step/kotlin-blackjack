package blackjack

import blackjack.domain.Dealer
import blackjack.domain.Deck
import blackjack.domain.GameResult
import blackjack.domain.Players
import blackjack.view.InputView
import blackjack.view.ResultView

fun main() {
    val deck = Deck.init()
    val players = InputView.getPlayers()
    val dealer = Dealer()
    val playersObject = Players(players)

    ResultView.firstDealCard(players, dealer)

    dealer.getFirstDealCards(deck.firstDraw())
    ResultView.showPlayerCards(dealer)

    playersObject.getFirstTwoCards(deck.firstDraw()) { player ->
        ResultView.showPlayerCards(player)
    }

    for (player in players) {
        while (InputView.askPlayer(player.name) && !player.isBusted) {
            player.hit(deck.draw())
            ResultView.showPlayerCards(player)
        }
    }

    ResultView.showPlayerResult(dealer.getCard(deck), dealer, players)
    ResultView.displayGameResult(GameResult(dealer, players))
}
