package blackjack

import blackjack.domain.player.Players
import blackjack.view.InputView

object Application {

    @JvmStatic
    fun main(args: Array<String>) {
        val players: Players = processInput { Players.getOrNull(InputView.readPlayers()) }
        val gameHost = GameHost(players)
        gameHost.start()
    }

    private fun <T> processInput(requestInput: () -> T?): T {
        var result: T?
        do {
            result = requestInput()
        } while (result == null)
        return result
    }
}
