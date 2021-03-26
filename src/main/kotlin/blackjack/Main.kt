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
    val players = inputView.getPlayerNames().map { Customer(it, inputView.getPlayerBetting(it)) }
    val blackJackGame = BlackJackGame()

    blackJackGame.startGame(players.plus(dealer))
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
    outputView.renderEarnings(dealer.match(players))
}
