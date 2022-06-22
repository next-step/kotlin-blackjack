package blackjack.controller

import blackjack.domain.player.Player
import blackjack.domain.player.Players
import blackjack.view.InputView

class BlackjackGameController {
    fun run() {
        val players = createPlayers()
    }

    private fun createPlayers(): Players {
        return InputView.inputPlayerNames()
            .map { Player(it) }
            .let { Players(it) }
    }
}
