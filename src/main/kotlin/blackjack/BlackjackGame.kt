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
        resultView.printInitialDistribute(dealer, players)
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
        while (player.currentStatus == Status.HIT && player.totalValue < BUST_SCORE) {
            val response = inputView.readHitOrStand(player.name)
            if (response == Status.HIT) {
                dealer.drawCard(player)
                resultView.printPlayerStatus(player)
            } else {
                player.updateStatus(Status.STAND)
            }
        }
    }

    companion object {
        private const val BUST_SCORE = 22
    }
}
