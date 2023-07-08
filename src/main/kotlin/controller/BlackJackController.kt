package controller

import domain.player.Dealer
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
        val remainPlayers = turn.playersCanTakeNextTurn().list.filter {
            InputView.askReceiveCard(it.name)
        }.let {
            Players(it)
        }

        turn = turn.proceed(remainPlayers) {
            ResultView.printDealerReceiveCardMessage()
        }

        ResultView.printResult(turn.dealer, players.list)

        if (turn is FinalTurn) {
            ResultView.printResult(turn.result(players))
            break
        }
    }
}
