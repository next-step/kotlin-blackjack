package blackjack

import blackjack.model.BlackJackGame
import blackjack.model.player.Dealer
import blackjack.model.player.Players
import blackjack.view.InputView
import blackjack.view.OutputView

fun main() {
    val dealer = Dealer()
    val gamers = InputView.getGamersName()
    val gamersWithBet = InputView.getBetAmount(dealer, gamers)

    val players = Players(listOf(dealer) + gamersWithBet)
    val blackJackGame = BlackJackGame(players)

    OutputView.firstTurn(players)
    blackJackGame.firstTurn()
    OutputView.printCardForPlayers(players)

    if (!players.checkGameDone()) {
        blackJackGame.progressTurn()
    }

    blackJackGame.checkPrize()

    OutputView.printPointResult(players)
    OutputView.printPrize(players)
}
