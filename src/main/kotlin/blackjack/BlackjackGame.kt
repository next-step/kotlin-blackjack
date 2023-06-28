package blackjack

import blackjack.dealer.Dealer
import blackjack.player.Player
import blackjack.player.Status
import blackjack.view.InputView
import blackjack.view.ResultView

class BlackjackGame(
    private val inputView: InputView,
    private val resultView: ResultView,
    private val dealer: Dealer = Dealer()
) {
    fun start() {
        val players = createPlayers()

        dealer.dealInitialCards(players)
        resultView.printInitialDistribute(players)
        resultView.printPlayerList(players)
        players.takeTurns()

        resultView.printFinalPlayerStatus(players)
    }

    private fun createPlayers(): List<Player> {
        val playerNames = inputView.readPlayers()
        return playerNames.map { Player(it) }
    }

    private fun List<Player>.takeTurns() {
        this.forEach { player ->
            takeTurn(player)
        }
    }

    private fun takeTurn(player: Player) {
        while (player.status == Status.WANT && player.getTotalValue() < 21) {
            val response = inputView.readWantOrStand(player.name)
            if (response == Status.WANT) {
                dealer.drawCard(player)
                resultView.printPlayerStatus(player)
            } else {
                player.updateStatus(Status.STAND)
            }
        }
    }
}
