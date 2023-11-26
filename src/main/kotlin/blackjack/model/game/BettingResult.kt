package blackjack.model.game

import blackjack.model.player.Player

data class BettingResult(val dealerBenefit: Double, val playerBenefits: Map<Player, Double>) {
    companion object {
        fun toResult(matchResult: MatchResult): BettingResult {
            val playerBenefits: Map<Player, Double> = matchResult.playerResults.mapValues { (player, rank) ->
                when {
                    rank == Rank.WIN && player.isBlackJack() -> player.bettingAmount * 1.5
                    rank == Rank.LOSE -> -player.bettingAmount
                    else -> player.bettingAmount
                }
            }

            return BettingResult(-playerBenefits.values.sum(), playerBenefits)
        }
    }
}
