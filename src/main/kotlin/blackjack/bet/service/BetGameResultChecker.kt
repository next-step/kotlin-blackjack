package blackjack.bet.service

import blackjack.bet.domain.BetDealer
import blackjack.bet.domain.BetPlayer
import blackjack.common.service.BlackJackDetermine

class BetGameResultChecker(
    private val dealer: BetDealer
) {
    fun determineGameResult(players: Array<BetPlayer>) {
        val dealerValue = dealer.optimalValue()

        players.forEach {
            val winner = BlackJackDetermine.determineWinner(it.optimalValue(), dealerValue)
            BetCalculator.updateScores(winner, it, dealer)
        }
    }
}
