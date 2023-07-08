package controller

import domain.Game
import domain.player.Player
import domain.player.Players
import presentation.InputView
import presentation.ResultView

fun main() {
    val playerNames = InputView.getPlayerNames()
    val players = Players(playerNames.map { Player(it) })

    val game = Game()
    game.start(players)

    ResultView.printInitialState(players.list)

    var remainPlayers = game.playersCanReceiveMoreCard(players)
    while (true) {
        val playersCanReceiveMoreCard = game.playersCanReceiveMoreCard(remainPlayers)
        if (playersCanReceiveMoreCard.noMorePlayer()) break

        remainPlayers = playersCanReceiveMoreCard.list.filter {
            InputView.askReceiveCard(it.name)
        }.let {
            Players(it)
        }

        game.hit(remainPlayers) { player ->
            ResultView.printPlayerState(player)
        }

        ResultView.printResult(players.list)
    }
}
