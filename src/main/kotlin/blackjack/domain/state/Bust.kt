package blackjack.domain.state

import blackjack.domain.Hand

class Bust(override val hand: Hand) : Finished(hand) {
    override fun earningRate() = BUST_EARNING_RATE

    companion object {
        private const val BUST_EARNING_RATE = -1.0
    }
}
