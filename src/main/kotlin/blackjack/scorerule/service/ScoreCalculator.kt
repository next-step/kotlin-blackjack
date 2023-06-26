package blackjack.scorerule.service

import blackjack.common.service.BlackJackDetermine
import blackjack.scorerule.domain.ScoreDealer
import blackjack.scorerule.domain.ScorePlayer

object ScoreCalculator {
    fun updateScores(winner: BlackJackDetermine.Winner, player: ScorePlayer, dealer: ScoreDealer) {
        when (winner) {
            BlackJackDetermine.Winner.PLAYER, BlackJackDetermine.Winner.DEALER_BUST -> {
                player.scoreBoard().countWin()
                dealer.scoreBoard().countLose()
            }
            BlackJackDetermine.Winner.DEALER -> {
                dealer.scoreBoard().countWin()
                player.scoreBoard().countLose()
            }
            else -> {
                dealer.scoreBoard().countDraw()
                player.scoreBoard().countDraw()
            }
        }
    }
}
