package blackjack.state

import blackjack.model.Cards

class Blackjack(cards: Cards) : Finished(cards) {

    override fun earningRate(): Double = EARNING_RATE

    companion object {
        private const val EARNING_RATE = 1.5
    }
}
