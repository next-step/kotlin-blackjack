package controller

import domain.gamer.Gamers
import domain.gamer.dealer.Dealer
import domain.gamer.player.Player
import domain.gamer.player.Players
import domain.turn.Game
import domain.turn.InitialTurn
import presentation.InputView
import presentation.ResultView

fun main() {
    val playerNames = InputView.getPlayerNames()
    val players = Players(playerNames.map { Player(it) })
    val dealer = Dealer()

    val game = Game(InitialTurn, Gamers.of(dealer, players)) {
        askPlayerWantToStay(it)
    }
    game.proceed()
    ResultView.printInitialState(dealer, players.list)
    dealer.onHit = {
        ResultView.printDealerReceiveCardMessage()
    }

    while (true) {
        game.proceed()
        if (game.isFinish) break
        ResultView.printResult(dealer, players.list)
    }

    game.result?.let {
        ResultView.printResult(it)
    }
}

private fun askPlayerWantToStay(name: String): Boolean {
    return !InputView.askReceiveCard(name)
}
