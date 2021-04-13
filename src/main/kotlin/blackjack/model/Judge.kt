package blackjack.model

import blackjack.model.score.Score

object Judge {
    fun calculateRevenue(gamerScore: Score, opponentScore: Score, gamerBet: Bet): Int {
        if (!opponentScore.isValid()) {
            return gamerBet.amount
        }

        if (!gamerScore.isValid()) {
            return -gamerBet.amount
        }

        if (gamerScore.isBlackJack && opponentScore.isBlackJack) {
            return 0
        }

        if (gamerScore.isBlackJack) {
            return (gamerBet.amount * 1.5).toInt()
        }

        if (opponentScore.isBlackJack) {
            return -gamerBet.amount
        }

        return if (gamerScore >= opponentScore) gamerBet.amount else -gamerBet.amount
    }
}
