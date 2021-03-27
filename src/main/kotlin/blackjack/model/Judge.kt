package blackjack.model

object Judge {
    fun isWin(playerScore: Score, opponentScore: Score): Boolean {
        if (!playerScore.isValid()) {
            return false
        }

        if (!opponentScore.isValid()) {
            return true
        }

        return playerScore >= opponentScore
    }
}
