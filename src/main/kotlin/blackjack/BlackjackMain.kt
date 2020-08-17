package blackjack

import blackjack.model.BlackJackGame
import blackjack.model.player.Dealer
import blackjack.view.InputView
import blackjack.view.OutputView

fun main() {
    val dealer = Dealer()
    val gamers = InputView.getGamersName()
    val gamersWithBet = InputView.getBetAmount(dealer, gamers)

    val players = getPlayers(dealer, gamersWithBet)
    val blackJackGame = BlackJackGame(players)

    OutputView.firstTurn(players)
    blackJackGame.firstTurn()
    OutputView.printCardForPlayers(players)

    if (!isGameDone(players)) {
        blackJackGame.progressTurn()
    }

    blackJackGame.checkPrize()

    OutputView.printPointResult(players)
    OutputView.printPrize(players)
}
