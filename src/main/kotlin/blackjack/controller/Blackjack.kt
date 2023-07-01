package blackjack.controller

import blackjack.GameCardStorage
import blackjack.Player
import blackjack.view.InputView
import blackjack.view.OutputView

class Blackjack(
    private val inputView: InputView,
    private val outputView: OutputView,
) {
    fun play(gameCardStorage: GameCardStorage) {
        val names = inputView.fetchPlayerNames()

        val players = names.map(::Player)

        players.forEach {
            it.take(gameCardStorage.drawCard())
            it.take(gameCardStorage.drawCard())
        }

        outputView.showInitialStatus(players)
    }
}

