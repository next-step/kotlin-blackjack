package blackjack

import blackjack.domain.Bet
import blackjack.domain.Deck
import blackjack.domain.member.Dealer
import blackjack.domain.member.Player
import blackjack.domain.member.Players
import blackjack.view.InputView
import blackjack.view.ResultView

fun main() {
    val usersNames = InputView.inputUsersNames()
    val deck = Deck.init()

    val players = Players(usersNames.map { name -> initPlayer(name, deck) })

    val dealer = Dealer.init(deck)
    ResultView.printDrawResults(dealer, players)

    for (player in players.items) {
        player.drawCardUntilWant(deck)
    }
    dealer.drawCardLimited(deck)

    ResultView.printPlayerResults(dealer)
    players.items.forEach { ResultView.printPlayerResults(it) }

    dealer.gameResult(players)
    val gameResult = dealer.gameResult(players)
    ResultView.printGamResult(gameResult)
}

private fun initPlayer(name: String, deck: Deck): Player {
    val inputBetMoney = InputView.inputBetMoney(name)
    val assignCards = deck.drawInitAssignCards()
    return Player(name, assignCards, Bet.of(inputBetMoney))
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
