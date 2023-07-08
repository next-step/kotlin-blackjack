package controller

import domain.dealer.Dealer
import domain.player.Player
import domain.player.Players
import domain.turn.Game
import domain.turn.InitialTurn
import presentation.InputView
import presentation.ResultView

fun main() {
    val playerNames = InputView.getPlayerNames()
    val players = Players(playerNames.map { Player(it) })
    val dealer = Dealer()

    val game = Game(InitialTurn, dealer, players)
    game.proceed()
    ResultView.printInitialState(dealer, players.list)
    dealer.addOnHitCallback {
        ResultView.printDealerReceiveCardMessage()
    }

    while (true) {
        game.playersCanTakeNextTurn().list.filterNot {
            InputView.askReceiveCard(it.name)
        }.forEach {
            it.stay()
        }

        game.proceed()

        if (game.isFinish) {
            game.result?.let {
                ResultView.printResult(it)
            }
            break
        }

        ResultView.printResult(dealer, players.list)
    }
}
