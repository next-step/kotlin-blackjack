package blackjack.scorerule.service

import blackjack.common.service.BlackJackDetermine
import blackjack.scorerule.domain.ScoreDealer
import blackjack.scorerule.domain.ScorePlayer

class ScoreGameResultChecker(
    private val dealer: ScoreDealer
) {
    fun determineGameResult(players: Array<ScorePlayer>) {
        val dealerValue = dealer.optimalValue()

        players.forEach {
            val winner = BlackJackDetermine.determineWinType(it, dealerValue)
            ScoreCalculator.updateScores(winner, it, dealer)
        }
    }
}
