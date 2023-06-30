package blackjack.domain

import blackjack.domain.player.Dealer
import blackjack.domain.player.GameResult

data class DealerResult(
    val dealer: Dealer,
    val result: List<DealerGameResultPair>,
) {

    data class DealerGameResultPair(
        val gameResult: GameResult,
        val count: Int,
    )

    companion object {
        private fun getGameResultPair(playerResults: PlayerResults, gameResult: GameResult): DealerGameResultPair {
            val count = playerResults.results.count { it.result == GameResult.valueOfOpposition(gameResult) }
            return DealerGameResultPair(gameResult, count)
        }

        fun from(dealer: Dealer, playerResults: PlayerResults): DealerResult {
            val gameResultPairs = GameResult.values().map { getGameResultPair(playerResults, it) }
            dealer.revenue.calcRevenue(playerResults)
            return DealerResult(dealer, gameResultPairs)
        }
    }
}
