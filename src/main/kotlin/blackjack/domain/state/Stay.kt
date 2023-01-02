package blackjack.domain.state

import blackjack.domain.Hand

class Stay(override val hand: Hand) : Finished(hand) {
    override fun earningRate() = DEFAULT_EARNING_RATE

    companion object {
        private const val DEFAULT_EARNING_RATE = 1.0
    }
}
