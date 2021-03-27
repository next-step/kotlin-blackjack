package blackjack.model

import blackjack.model.score.Score

object Judge {
    fun isWin(playerScore: Score, opponentScore: Score): Boolean {
        if (!opponentScore.isValid()) {
            return true
        }

        if (!playerScore.isValid()) {
            return false
        }

        return playerScore >= opponentScore
    }
}
