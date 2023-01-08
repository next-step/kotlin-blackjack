package blackjack.domain.state

import blackjack.domain.Hand

class Blackjack(override val hand: Hand) : Finished(hand) {
    override fun earningRate() = BLACKJACK_EARNING_RATE

    companion object {
        private const val BLACKJACK_EARNING_RATE = 1.5
    }
}
