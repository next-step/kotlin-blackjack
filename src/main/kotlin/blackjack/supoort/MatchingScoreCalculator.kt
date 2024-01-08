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
        val bettingAmount = players.fold(BettingAmount(0)) { acc, player ->
            val updatedAmount = when {
                player.status is Blackjack && dealer.status !is Blackjack -> acc - player.status.calculateBettingAmount(Result.Win, player.bettingAmount)
                player.status is Bust -> acc + player.bettingAmount
                dealer.status is Bust -> acc - player.bettingAmount
                dealer.resultScore() > player.resultScore() -> acc + player.bettingAmount
                else -> acc
            }
            BettingAmount(updatedAmount.amount)
        }

        return bettingAmount
    }
}
