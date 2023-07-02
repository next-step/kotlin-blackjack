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
        val playerReceiveMoreCard = game.playersCanReceiveMoreCard()
        if (playerReceiveMoreCard.isEmpty()) break

        playerReceiveMoreCard.receiveMoreCard(game)

        ResultView.printResult(game.players)
    }
}

private fun List<Player>.receiveMoreCard(game: Game) {
    forEach {
        it.receiveMoreCard(game)
    }
}

private fun Player.receiveMoreCard(game: Game) {
    if (InputView.askReceiveCard(name)) {
        game.dealAdditionalCard(this)
        ResultView.printPlayerState(this)
    }
}
