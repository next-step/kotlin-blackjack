package blackjack.domain.state

import blackjack.domain.player.DealerState

class Stay(hands: Hands) : Finished(hands) {
    override val profitRate: Double = 1.0

    override fun profit(
        betAmount: Int,
        dealerState: DealerState,
    ): Double {
        return when {
            dealerState.isBlackjack -> -betAmount.toDouble()
            dealerState.isBust -> betAmount * profitRate
            else -> betAmount * profitRate
        }
    }
}
