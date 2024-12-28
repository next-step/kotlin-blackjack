package blackjack.domain.state

import blackjack.domain.player.DealerState

class Blackjack(hands: Hands) : Finished(hands) {
    override val profitRate: Double = 1.5

    override fun profit(
        betAmount: Int,
        dealerState: DealerState,
    ): Double {
        return when {
            dealerState.isBlackjack -> betAmount * 1.0
            dealerState.isBust -> betAmount * profitRate
            else -> betAmount * profitRate
        }
    }
}
