package controller

import domain.dealer.Dealer
import domain.player.Player
import domain.player.Players
import domain.turn.FinalTurn
import domain.turn.InitialTurn
import presentation.InputView
import presentation.ResultView

fun main() {
    val playerNames = InputView.getPlayerNames()
    val players = Players(playerNames.map { Player(it) })

    var turn = InitialTurn(Dealer(), players).proceed()
    ResultView.printInitialState(turn.dealer, players.list)

    while (true) {
        turn.playersCanTakeNextTurn().list.filterNot {
            InputView.askReceiveCard(it.name)
        }.forEach {
            it.stay()
        }

        turn = turn.proceed {
            ResultView.printDealerReceiveCardMessage()
        }

        if (turn is FinalTurn) {
            ResultView.printResult(turn.result(players))
            break
        }

        ResultView.printResult(turn.dealer, players.list)
    }
}
