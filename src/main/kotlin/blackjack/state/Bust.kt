package blackjack.state

import blackjack.model.Cards

class Bust(cards: Cards) : Finished(cards) {

    override fun earningRate(): Double = EARNING_RATE

    companion object {
        private const val EARNING_RATE = 0.0
    }
}
