package blackjack

import blackjack.model.BlackJackGame
import blackjack.model.player.Dealer
import blackjack.model.player.Gamer
import blackjack.model.player.Player
import blackjack.model.player.Players
import blackjack.view.InputView
import blackjack.view.OutputView

fun main() {
    val gamers = InputView.getGamersName()
    val gamersWithBet = InputView.getBetAmount(gamers)
    val players = getPlayers(gamersWithBet)
    val blackJackGame = BlackJackGame(players)

    OutputView.firstTurn(players)
    blackJackGame.firstTurn()
    OutputView.printCardForPlayers(players)

    blackJackGame.progressTurn()
    blackJackGame.checkWinOrLose()

    OutputView.printPoint(players)
    OutputView.printWinOrLost(players)
}

fun getPlayers(gamers: List<Gamer>): Players {
    val players = mutableListOf<Player>()

    players.add(Dealer())
    players.addAll(gamers)

    return Players(players)
}
