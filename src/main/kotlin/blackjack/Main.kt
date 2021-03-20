package blackjack

import blackjack.domain.BlackJackGame
import blackjack.domain.player.Customer
import blackjack.domain.player.Dealer
import blackjack.view.InputView
import blackjack.view.OutputView

fun main() {
    val inputView = InputView()
    val outputView = OutputView()

    val dealer = Dealer()
    val players = inputView.getPlayerNames().map { Customer(it) }
    val blackJackGame = BlackJackGame()

    blackJackGame.startGame(listOf(dealer, players))
    outputView.renderCards(dealer, players)

    players.forEach {
        while (it.canHit() && inputView.isHit(it)) {
            blackJackGame.hit(it)
            outputView.renderPlayerCards(it)
        }
    }

    while (dealer.canHit()) {
        blackJackGame.hit(dealer)
        outputView.renderDealerHit()
    }

    outputView.renderCardsAndScore(dealer, players)
    outputView.renderResult(dealer.match(players))
}

fun <T> listOf(t: T, list: List<T>): List<T> {
    val list = mutableListOf<T>(t)
    list.addAll(list)
    return list.toList()
}
