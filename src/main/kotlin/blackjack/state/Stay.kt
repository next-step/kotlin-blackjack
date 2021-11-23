package blackjack.state

import blackjack.model.Amount
import blackjack.model.Cards
import blackjack.model.EarningRate
import blackjack.model.Profit

class Stay(cards: Cards) : Finished(cards) {

    override fun earningRate(): EarningRate = EarningRate(EARNING_RATE)

    override fun profit(amount: Amount, state: State): Profit {
        val profit = Profit(amount * earningRate())
        return when {
            state is Bust -> profit
            cards.sum() >= state.cards.sum() -> profit
            else -> -profit
        }
    }

    companion object {
        private const val EARNING_RATE = 1.0
    }
}
