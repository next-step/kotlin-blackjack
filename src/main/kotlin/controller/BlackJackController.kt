package controller

import domain.Game
import domain.player.Player
import domain.player.Players
import presentation.InputView
import presentation.ResultView

fun main() {
    val playerNames = InputView.getPlayerNames()
    val players = Players(playerNames.map { Player(it) })

    val game = Game(players)
    game.start()

    ResultView.printInitialState(players.list)

    while (true) {
        val playersCanReceiveMoreCard = game.playersCanReceiveMoreCard()
        if (playersCanReceiveMoreCard.noMorePlayer()) break

        val remainPlayers = playersCanReceiveMoreCard.list.filter {
            InputView.askReceiveCard(it.name)
        }.let {
            Players(it)
        }

        game.updateRemainPlayers(remainPlayers)
        game.hit()

        ResultView.printResult(players.list)
    }
}
