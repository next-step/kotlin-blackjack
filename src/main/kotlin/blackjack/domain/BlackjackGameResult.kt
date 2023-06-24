package blackjack.domain

import blackjack.domain.player.Dealer
import blackjack.domain.player.GameResult
import blackjack.domain.player.Player

data class BlackjackGameResult(
    val dealer: DealerResult,
    val players: List<PlayerResult>,
)

data class PlayerResult(
    val player: Player,
    val result: GameResult,
)

data class DealerResult(
    val dealer: Dealer,
    val result: List<DealerGameResultPair>,
) {
    data class DealerGameResultPair(
        val gameResult: GameResult,
        val count: Int,
    )

    fun getMatchedGameResultCount(gameResult: GameResult): Int {
        return result.first { it.gameResult == gameResult }.count
    }

    companion object {
        private fun getGameResultPair(playerResults: List<PlayerResult>, gameResult: GameResult): DealerGameResultPair {
            val count = playerResults.count { it.result == GameResult.valueOfOpposition(gameResult) }
            return DealerGameResultPair(gameResult, count)
        }

        fun from(dealer: Dealer, playerResults: List<PlayerResult>): DealerResult {
            val gameResultPairs = GameResult.values().map { getGameResultPair(playerResults, it) }
            return DealerResult(dealer, gameResultPairs)
        }
    }
}
