package blackjack.common

import blackjack.domain.bet.DealerProfit
import blackjack.domain.bet.PlayerProfit
import blackjack.domain.bet.Profits
import blackjack.domain.player.Dealer

class ProfitSummary(profits: Profits) {
    val values: List<Pair<String, Double>> =
        listOf(profits.dealer.toProfitSummary()) +
            profits.players.map { it.toProfitSummary() }

    private fun DealerProfit.toProfitSummary(): Pair<String, Double> = Dealer.DEALER_NAME to profit.value

    private fun PlayerProfit.toProfitSummary(): Pair<String, Double> = playerName to profit.value
}
