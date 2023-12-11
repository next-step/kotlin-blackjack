package blackjack.supoort

import blackjack.participant.Dealer
import blackjack.participant.Name
import blackjack.participant.Player
import blackjack.participant.Result
import blackjack.participant.status.Blackjack
import blackjack.participant.status.Bust

object GameResultGenerator {

    fun generateGameResult(players: List<Player>, dealer: Dealer): GameResult {
        val dealerBettingAmount = MatchingScoreCalculator.matchingScoreForDealer(players, dealer)
        val resultMap = mapOf(Name("딜러") to dealerBettingAmount) + players.map { player ->
            val result = matchingResult(player, dealer)
            val bettingAmount = player.status.calculateBettingAmount(result, player.bettingAmount)
            player.name to bettingAmount
        }

        return GameResult(resultMap)
    }

    private fun matchingResult(player: Player, dealer: Dealer): Result {
        if (player.status is Blackjack) {
            return when (dealer.status) {
                is Blackjack -> Result.Lose
                else -> {
                    return Result.Win
                }
            }
        }

        if (player.status is Bust) {
            return Result.Lose
        }

        if (dealer.status is Bust) {
            return Result.Win
        }

        return when (dealer.resultScore() > player.resultScore()) {
            true -> {
                Result.Lose
            }

            else -> Result.Win
        }
    }
}
