package blackjack.model

import blackjack.model.score.Score

object Judge {
    fun isWin(gamerScore: Score, opponentScore: Score): Boolean {
        if (!opponentScore.isValid()) {
            return true
        }

        if (!gamerScore.isValid()) {
            return false
        }

        if (gamerScore.isMaximum() && opponentScore.isMaximum()) {
            return gamerScore.isBlackJack || !opponentScore.isBlackJack
        }

        return gamerScore >= opponentScore
    }
}
