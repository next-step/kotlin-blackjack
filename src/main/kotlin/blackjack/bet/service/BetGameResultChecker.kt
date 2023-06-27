package blackjack.bet.service

import blackjack.bet.domain.BetDealer
import blackjack.bet.domain.BetPlayer
import blackjack.common.service.BlackJackDetermine

class BetGameResultChecker(
    private val dealer: BetDealer
) {
    fun determine(players: List<BetPlayer>) {
        val dealerValue = dealer.optimalValue()

        for (player in players) {
            val winType = BlackJackDetermine.determineWinType(player, dealerValue)
            BetCalculator.settleBet(winType, player, dealer)
        }
    }
}
