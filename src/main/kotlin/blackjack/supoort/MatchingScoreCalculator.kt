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

    fun matchingScore(player: Player, dealer: Dealer): Result {
        var bettingAmount = BettingAmount(0)
        if (player.status is Blackjack) {
            return when (dealer.status) {
                is Blackjack -> Result.Lose
                else -> {
                    bettingAmount -= player.status.calculateBettingAmount(
                        Result.Win,
                        player.bettingAmount
                    )

                    return Result.Win
                }
            }
        }

        if (player.status is Bust) {
            bettingAmount += player.bettingAmount
            return Result.Lose
        }

        if (dealer.status is Bust) {
            bettingAmount -= player.bettingAmount
            return Result.Win
        }

        return when (dealer.resultScore() > player.resultScore()) {
            true -> {
                bettingAmount += player.bettingAmount
                Result.Lose
            }

            else -> Result.Win
        }
    }
}