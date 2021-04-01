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

        return gamerScore >= opponentScore
    }
}
