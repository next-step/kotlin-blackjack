package blackjack.state

import blackjack.model.Cards
import blackjack.model.EarningRate

class Blackjack(cards: Cards) : Finished(cards) {

    override fun earningRate(): EarningRate = EarningRate(EARNING_RATE)

    companion object {
        private const val EARNING_RATE = 1.5
    }
}
