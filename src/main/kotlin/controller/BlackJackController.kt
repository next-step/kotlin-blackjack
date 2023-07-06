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

    while (true) {
        val playerReceiveMoreCard = Players(game.playersCanReceiveMoreCard())
        if (playerReceiveMoreCard.noMorePlayer()) break

        playerReceiveMoreCard.dealMoreCard(game)

        ResultView.printResult(game.players)
    }
}
