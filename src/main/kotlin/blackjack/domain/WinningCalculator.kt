package blackjack.domain

import blackjack.domain.player.Dealer
import blackjack.domain.player.Player
import blackjack.domain.player.Players
import blackjack.domain.status.GameResult

object WinningCalculator {
    fun calculatorGameResult(
        players: Players,
        dealer: Dealer,
    ) {
        // 딜러는 21초과시 딜러 승
        if (dealer.isBust()) {
            handleDealerBust(players, dealer)
            return
        }

        val dealerWinCount = calculatePlayerResults(players, dealer)
        dealer.updateWinningStatus(result = GameResult.WIN, count = dealerWinCount)
        dealer.updateWinningStatus(result = GameResult.LOSE, count = players.size - dealerWinCount)
    }

    private fun handleDealerBust(
        players: Players,
        dealer: Dealer,
    ) {
        dealer.updateWinningStatus(result = GameResult.WIN, count = players.size)
        players.updateWinningStatus(result = GameResult.LOSE)
    }

    private fun calculatePlayerResults(
        players: Players,
        dealer: Dealer,
    ): Int {
        var dealerWinCount = 0
        players.forEach {
            val result = calculateResult(dealer, it)
            it.updateWinningStatus(result)
            if (result == GameResult.LOSE) {
                dealerWinCount++
            }
        }
        return dealerWinCount
    }

    private fun calculateResult(
        dealer: Dealer,
        player: Player,
    ): GameResult {
        return when {
            player.isBust() || dealer.calculateCard() > player.calculateCard() -> GameResult.LOSE
            else -> GameResult.WIN
        }
    }
}
