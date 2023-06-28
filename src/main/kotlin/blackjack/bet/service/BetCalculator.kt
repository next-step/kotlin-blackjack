package blackjack.bet.service

import blackjack.bet.domain.BetDealer
import blackjack.bet.domain.BetPlayer
import blackjack.bet.domain.WinType

object BetCalculator {
    fun settleBet(winType: WinType, player: BetPlayer, dealer: BetDealer) {
        val playerIncome = (player.wallet().bettingAmount() * winType.incomeRatio).toInt()
        val dealerIncome = playerIncome * -1
        player.wallet().settle(playerIncome)
        dealer.wallet().settle(dealerIncome)
    }
}
