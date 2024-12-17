package blackjack.domain

import blackjack.domain.player.Dealer
import blackjack.domain.player.Players

object WinningCalculator {
    fun calculatorGameResult(players: Players, dealer: Dealer) {
        // 딜러는 21초과시 딜러 승
        if (dealer.isBust()) {
            dealer.updateWinningStatus(players.size, 0)
            players.forEach { it.updateWinningStatus(winCount = 0, loseCount = 1) }
            return
        }

        val losePlayers = players.getLosePlayers(dealer.calculateCard())
        losePlayers.forEach { it.updateWinningStatus(winCount = 0, loseCount = 1) }

        players.forEach {
            if(losePlayers.contains(it)) it.updateWinningStatus(winCount = 0, loseCount = 1)
            else it.updateWinningStatus(winCount = 1, loseCount = 0)
        }

        dealer.updateWinningStatus(winCount = players.size - losePlayers.size, loseCount = losePlayers.size)
    }
}
