package blackjack.model

import blackjack.model.score.Score

object Judge {
    fun calculateRevenue(gamerScore: Score, opponentScore: Score, gamerBet: Bet): Bet {
        if (!opponentScore.isValid()) {
            return gamerBet
        }

        if (!gamerScore.isValid()) {
            return -gamerBet
        }

        if (gamerScore.isBlackJack && opponentScore.isBlackJack) {
            return Bet.ZERO
        }

        if (gamerScore.isBlackJack) {
            return (gamerBet * 1.5)
        }

        if (opponentScore.isBlackJack) {
            return -gamerBet
        }

        return if (gamerScore >= opponentScore) gamerBet else -gamerBet
    }
}
