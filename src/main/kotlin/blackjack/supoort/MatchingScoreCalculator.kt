package blackjack.supoort

import blackjack.participant.BettingAmount
import blackjack.participant.Dealer
import blackjack.participant.Player
import blackjack.participant.Result
import blackjack.participant.minus
import blackjack.participant.plus
import blackjack.participant.status.Blackjack
import blackjack.participant.status.Bust

object MatchingScoreCalculator {

    fun matchingScoreForDealer(players: List<Player>, dealer: Dealer): BettingAmount {
        var bettingAmount = BettingAmount(0)
        players.forEach {
            if (it.status is Blackjack && dealer.status !is Blackjack) {
                bettingAmount -= it.status.calculateBettingAmount(
                    Result.Win,
                    it.bettingAmount
                )
            }

            if (it.status is Bust) {
                bettingAmount += it.bettingAmount
            }

            if (dealer.status is Bust) {
                bettingAmount -= it.bettingAmount
            }

            if (dealer.resultScore() > it.resultScore()) {
                bettingAmount += it.bettingAmount
            }
        }
        return bettingAmount
    }
}
