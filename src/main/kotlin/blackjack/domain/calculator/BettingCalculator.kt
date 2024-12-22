package blackjack.domain.calculator

import blackjack.domain.player.Dealer
import blackjack.domain.player.Player
import blackjack.domain.player.Players

object BettingCalculator {

    fun calculateBettingAmount(players: Players, dealer: Dealer) {
        var dealerMoney = 0f
        players.forEach { player ->
            val betMoney = calculateMoney(player)
            player.updateBetMoney(betMoney)
            dealerMoney += (-betMoney)
        }
        dealer.updateBetMoney(dealerMoney)
    }

    private fun calculateMoney(player: Player): Float {
        return when {
            player.gameResult.getWinCount() > 0 && player.getAllCards().size == 2 -> (player.initBet * 1.5).toFloat()
            player.gameResult.getWinCount() > 0 -> player.initBet
            player.gameResult.getLoseCount() > 0 -> -player.initBet
            else -> player.initBet
        }
    }
}