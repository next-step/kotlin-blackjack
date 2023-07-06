package blackjack.game

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
        val gameEvaluator = GameEvaluator()
        dealer.dealInitialCards(players)
        resultView.printInitialDistribute(dealer, players)
        resultView.printPlayerList(players)
        players.takeTurns()
        checkDealerStatus()
        resultView.printFinalDealerStatus(dealer)
        resultView.printFinalPlayerStatus(players)

        val gameResult = gameEvaluator.evaluate(dealer, players)
        resultView.printMatchResult(gameResult.getMatchResult)
    }

    private fun createPlayers(): List<Player> {
        val playerNames = inputView.readPlayers()
        val bettingMoney = playerNames.map { inputView.readBettingMoney(it) }
        val playerBettingMoney = playerNames.zip(bettingMoney)
        return playerBettingMoney.map { Player(it.first, it.second) }
    }

    private fun List<Player>.takeTurns() {
        this.forEach { player ->
            takeTurn(player)
        }
    }

    private fun takeTurn(player: Player) {
        while (player.currentStatus == Status.HIT && player.totalValue <= BLACKJACK_SCORE) {
            val response = inputView.readHitOrStand(player.name)
            if (response == Status.HIT) {
                dealer.drawCard(player)
                resultView.printPlayerStatus(player)
            } else {
                player.updateStatus(Status.STAND)
            }
        }
    }

    private fun checkDealerStatus() {
        if (dealer.totalValue <= DEALER_HIT_SCORE) {
            dealer.drawCard(dealer)
            resultView.printDealerStatus()
        }
    }

    companion object {
        private const val DEALER_HIT_SCORE = 16
        const val BLACKJACK_SCORE = 21
    }
}
