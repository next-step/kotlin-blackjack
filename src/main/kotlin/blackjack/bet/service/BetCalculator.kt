package blackjack.bet.service

import blackjack.bet.domain.BetDealer
import blackjack.bet.domain.BetPlayer
import blackjack.bet.domain.WinType

object BetCalculator {
    fun settleBetMoney(winType: WinType, player: BetPlayer, dealer: BetDealer) {
        val betAmount = player.wallet().balance()
        val winningAmount = (betAmount * winType.payoutRatio).toInt()
        val dealerAmount = winningAmount * -1

        player.wallet().settle(winningAmount)
        dealer.wallet().settle(dealerAmount)
    }
}
