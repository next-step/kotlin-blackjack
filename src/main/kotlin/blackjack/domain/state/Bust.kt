package blackjack.domain.state

import blackjack.domain.player.DealerState

class Bust(hands: Hands) : Finished(hands) {
    override val profitRate: Double = 0.0

    override fun profit(
        betAmount: Int,
        dealerState: DealerState,
    ): Double {
        return if (dealerState.isBust) 0.0 else -betAmount.toDouble()
    }
}
