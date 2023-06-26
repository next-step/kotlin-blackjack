package blackjack.service

import blackjack.domain.Dealer
import blackjack.domain.Player

class GameResultChecker(
    private val dealer: Dealer
) {
    fun determineGameResult(players: Array<Player>) {
        val dealerValue = dealer.optimalValue()

        players.forEach {
            val winner = ScoreCalculator.determineWinner(it.optimalValue(), dealerValue)
            ScoreCalculator.updateScores(winner, it, dealer)
        }
    }
}
