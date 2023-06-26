package blackjack.service

import blackjack.domain.Dealer
import blackjack.domain.Player

object ScoreCalculator {
    fun determineWinner(playerValue: Int, dealerValue: Int): Winner {
        return when {
            playerValue > 21 -> Winner.DEALER
            dealerValue > 21 -> Winner.PLAYER
            playerValue > dealerValue -> Winner.PLAYER
            playerValue < dealerValue -> Winner.DEALER
            else -> Winner.DRAW
        }
    }

    fun updateScores(winner: Winner, player: Player, dealer: Dealer) {
        when (winner) {
            Winner.PLAYER -> {
                player.scoreBoard().countWin()
                dealer.scoreBoard().countLose()
            }
            Winner.DEALER -> {
                dealer.scoreBoard().countWin()
                player.scoreBoard().countLose()
            }
            Winner.DRAW -> {
                dealer.scoreBoard().countDraw()
                player.scoreBoard().countDraw()
            }
        }
    }

    enum class Winner {
        PLAYER, DEALER, DRAW
    }
}
