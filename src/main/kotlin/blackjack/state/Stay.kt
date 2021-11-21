package blackjack.state

import blackjack.model.Cards

class Stay(cards: Cards) : Finished(cards) {

    override fun earningRate(): Double = EARNING_RATE

    companion object {
        private const val EARNING_RATE = 1.0
    }
}
