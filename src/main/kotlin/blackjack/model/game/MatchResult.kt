package blackjack.model.game

import blackjack.model.player.Dealer
import blackjack.model.player.Player

data class MatchResult(val dealerResult: List<Rank>, val playerResults: Map<Player, Rank>) {
    val dealerBenefit: Double = -playerBenefits().values.sum()

    fun playerBenefits(): Map<Player, Double> = playerResults.mapValues { (player, rank) ->
        when {
            rank == Rank.WIN && player.isBlackJack() -> player.bettingAmount * 1.5
            rank == Rank.LOSE -> -player.bettingAmount
            else -> player.bettingAmount
        }
    }

    companion object {
        fun toResult(dealer: Dealer, players: List<Player>): MatchResult {
            val playerResultsMap = players.associateWith { player ->
                dealer.isWin(player)
            }

            val dealerResults = playerResultsMap.map { !it.value }

            return MatchResult(dealerResults, playerResultsMap)
        }
    }
}
