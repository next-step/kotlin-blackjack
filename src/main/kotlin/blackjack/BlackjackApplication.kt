package blackjack

import blackjack.domain.Players
import blackjack.view.InputConsoleView
import blackjack.view.OutputConsoleView

object BlackjackApplication {
    private val inputConsoleView = InputConsoleView()
    private val outputConsoleView = OutputConsoleView()

    @JvmStatic
    fun main(args: Array<String>) {
        val namesOfPlayers = inputConsoleView.namesOfPlayers()
        val players = Players(namesOfPlayers)

        outputConsoleView.printInitCardMsg(players)
        players.players.map { player ->
            while (player.cardSum() < 21 && inputConsoleView.wannaGetNextCard(player)) {
                players.getCard(player)
                outputConsoleView.printCards(player)
            }
        }

        outputConsoleView.printResult(players)
    }
}
