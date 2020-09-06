package blackjack

import blackjack.domain.player.Players
import blackjack.view.InputView

object Application {

    @JvmStatic
    fun main(args: Array<String>) {
        val playersName = InputView.readPlayers()
        val players = Players(playersName)
        GameHost(players).start()
    }
}
