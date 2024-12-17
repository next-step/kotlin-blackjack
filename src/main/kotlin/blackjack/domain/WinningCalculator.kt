package blackjack.domain

import blackjack.domain.player.Dealer
import blackjack.domain.player.Players

object WinningCalculator {
    fun calculatorGameResult(players: Players, dealer: Dealer) {
        // 딜러는 21초과시 딜러 승
        if (dealer.isBust()) {
            dealer.updateWinningStatus(players.size, 0)
            return
        }

        var dealerWinning = 0
        var dealerLosing = 0
        players.forEach { player ->
            if (player.isBust() || dealer.calculateCard() > player.calculateCard()) {
                dealerWinning++
                player.updateWinningStatus(0, 1)
            } else {
                dealerLosing++
                player.updateWinningStatus(1, 0)
            }
        }

        dealer.updateWinningStatus(dealerWinning, dealerLosing)
    }
}
