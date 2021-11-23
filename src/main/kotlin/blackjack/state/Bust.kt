package blackjack.state

import blackjack.model.Amount
import blackjack.model.Cards
import blackjack.model.EarningRate
import blackjack.model.Profit

class Bust(cards: Cards) : Finished(cards) {

    override fun earningRate(): EarningRate = EarningRate(EARNING_RATE)

    override fun profit(amount: Amount, state: State): Profit = Profit(amount * earningRate())

    companion object {
        private const val EARNING_RATE = -1.0
    }
}
