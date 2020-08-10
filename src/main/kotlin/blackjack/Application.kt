package blackjack

import blackjack.domain.player.Players
import blackjack.view.InputView
import blackjack.view.ResultView

object Application {

    @JvmStatic
    fun main(args: Array<String>) {
        val players: Players = processInput { requestPlayers() }
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

    private fun requestPlayers() = try {
        Players(InputView.readPlayers())
    } catch (e: IllegalArgumentException) {
        ResultView.printInvalidName()
        null
    }
}
