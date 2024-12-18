package blackjack.domain

import blackjack.entity.Dealer

data class DealerResult private constructor(
    val dealerName: String,
    val dealerProfitAmount: Int,
) {
    companion object {
        fun from(
            dealer: Dealer,
            playerResults: List<PlayerResult>,
        ): DealerResult {
            val profit = playerResults.sumOf { -it.playerProfitAmount }

            return DealerResult(
                dealerName = dealer.name,
                dealerProfitAmount = profit,
            )
        }
    }
}
