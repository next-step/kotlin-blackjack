package controller

import domain.Turn
import domain.player.Player
import domain.player.Players
import presentation.InputView
import presentation.ResultView

fun main() {
    val playerNames = InputView.getPlayerNames()
    val players = Players(playerNames.map { Player(it) })

    val turn = Turn(players)
    turn.proceedInitialTurn()

    ResultView.printInitialState(players.list)

    while (true) {
        val playersCanReceiveMoreCard = turn.playersCanTakeNextTurn()
        if (playersCanReceiveMoreCard.noMorePlayer()) break

        val remainPlayers = playersCanReceiveMoreCard.list.filter {
            InputView.askReceiveCard(it.name)
        }.let {
            Players(it)
        }

        turn.proceedNextTurn(remainPlayers)

        ResultView.printResult(players.list)
    }
}
