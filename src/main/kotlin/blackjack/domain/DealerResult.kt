package blackjack.domain

import blackjack.entity.Dealer

data class DealerResult(
    val dealerName: String,
    val winCount: Int,
    val loseCount: Int,
    val drawCount: Int,
) {
    companion object {
        fun from(
            dealer: Dealer,
            playerResults: List<PlayerResult>,
        ): DealerResult {
            val winCount = playerResults.count { it.loseCount > 0 }
            val loseCount = playerResults.count { it.winCount > 0 }
            val drawCount = playerResults.count { it.drawCount > 0 }

            return DealerResult(
                dealerName = dealer.name,
                winCount = winCount,
                loseCount = loseCount,
                drawCount = drawCount,
            )
        }
    }
}
