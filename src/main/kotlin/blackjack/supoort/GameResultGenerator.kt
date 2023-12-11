package blackjack.supoort

import blackjack.participant.BettingAmount
import blackjack.participant.Dealer
import blackjack.participant.Name
import blackjack.participant.Player
import blackjack.participant.Result
import blackjack.participant.minus
import blackjack.participant.plus
import blackjack.participant.status.Blackjack
import blackjack.participant.status.Bust

object GameResultGenerator {

    fun generateGameResult(players: List<Player>, dealer: Dealer): GameResult {
        val resultMap = mapOf(Name("딜러") to dealer.bettingAmount) + players.map { player ->
//            val result = dealer.matchingScore(player)
            val result = matchingScore(player, dealer)
            val bettingAmount = player.status.calculateBettingAmount(result, player.bettingAmount)
            player.name to bettingAmount
        }

        return GameResult(resultMap)
    }

    private fun matchingScore(player: Player, dealer: Dealer): Result {
        var bettingAmount: BettingAmount = BettingAmount(0)
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