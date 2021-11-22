package blackjack.controller

import blackjack.domain.Players
import blackjack.view.InputView

object GameController {

    fun start() {
        val playerNames = InputView.inputPlayerNames()
        val players = Players.of(*Players.getPlayerListByNames(playerNames).toTypedArray())
    }
}
