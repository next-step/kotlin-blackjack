package controller

import domain.Game
import domain.Player
import presentation.InputView
import presentation.ResultView

fun main() {
    val game = Game(
        InputView.getPlayerNames()
            .map { Player(it) }
    )
    game.start()
    ResultView.printInitialState(game.players)

    val playerReceiveMoreCard = game.playersCanReceiveMoreCard()
        .playerReceiveMoreCard()
}

fun List<Player>.playerReceiveMoreCard(): List<Player> {
    return filter {
        InputView.askReceiveCard(it.name)
    }
}
