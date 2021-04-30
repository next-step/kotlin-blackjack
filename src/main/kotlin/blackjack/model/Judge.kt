package blackjack.model

import blackjack.model.gamer.Gamer
import blackjack.model.gamer.Gamers
import blackjack.model.score.Score

object Judge {
    private const val BLACK_JACK_TIMES = 1.5

    fun calculateRevenue(gamers: Gamers, opponent: Gamer, rule: Rule): BetMoney {
        return gamers.fold(BetMoney.ZERO) { betSum, gamer ->
            betSum + calculateRevenue(rule.getScore(gamer.cards), rule.getScore(opponent.cards), gamer.betMoney)
        }
    }

    fun calculateRevenue(gamerScore: Score, opponentScore: Score, gamerBetMoney: BetMoney): BetMoney {
        if (!opponentScore.isValid()) {
            return gamerBetMoney
        }

        if (!gamerScore.isValid()) {
            return -gamerBetMoney
        }

        if (gamerScore.isBlackJack && opponentScore.isBlackJack) {
            return BetMoney.ZERO
        }

        if (gamerScore.isBlackJack) {
            return (gamerBetMoney * BLACK_JACK_TIMES)
        }

        if (opponentScore.isBlackJack) {
            return -gamerBetMoney
        }

        return if (gamerScore >= opponentScore) gamerBetMoney else -gamerBetMoney
    }
}
