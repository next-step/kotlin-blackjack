package blackjack.controller

import blackjack.domain.Players
import blackjack.view.InputView

object GameController {

    const val BLACK_JACK_SCORE = 21

    fun start() {
        val playerNames = InputView.inputPlayerNames()
        val players = Players.of(*Players.getPlayerListByNames(playerNames).toTypedArray())
    }
}
