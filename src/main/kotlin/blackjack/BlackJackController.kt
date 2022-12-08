package blackjack

import blackjack.domain.Dealer
import blackjack.domain.Deck
import blackjack.domain.Player
import blackjack.domain.Players
import blackjack.domain.Result
import blackjack.view.InputView
import blackjack.view.ResultView

fun main() {
    val usersNames = InputView.inputUsersNames()
    val deck = Deck.init()

    val dealer = Dealer.init(deck)
    val players = Players.init(usersNames, deck)
    ResultView.printDrawResults(dealer, players)

    for (player in players.items) {
        player.drawCardUntilWant(deck)
    }
    dealer.drawCardLimited(deck)

    ResultView.printPlayerResults(dealer)
    players.items.forEach { ResultView.printPlayerResults(it) }

    val gameResult = Result.init(dealer, players)
    ResultView.printGamResult(gameResult)
}

private fun Player.drawCardUntilWant(deck: Deck) {
    while (this.ableMoreDrawCard() && InputView.checkWantDrawMoreCard(this)) {
        this.addCard(deck.draw())
        ResultView.printCardScoreDescription(this)
    }
}

private fun Dealer.drawCardLimited(deck: Deck) {
    while (this.ableMoreDrawCard()) {
        this.addCard(deck.draw())
        ResultView.printDealerDrawCardDescription()
    }
}
