package blackjack.scorerule.service

import blackjack.bet.domain.WinType
import blackjack.scorerule.domain.ScoreDealer
import blackjack.scorerule.domain.ScorePlayer

object ScoreCalculator {
    fun updateScores(winType: WinType, player: ScorePlayer, dealer: ScoreDealer) {
        when (winType) {
            WinType.PLAYER_WIN, WinType.DEALER_BUST -> {
                player.scoreBoard().countWin()
                dealer.scoreBoard().countLose()
            }
            WinType.DEALER_WIN -> {
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
