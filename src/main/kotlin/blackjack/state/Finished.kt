package blackjack.state

import blackjack.model.Amount
import blackjack.model.Card
import blackjack.model.Cards
import blackjack.model.EarningRate
import blackjack.model.Profit

abstract class Finished(cards: Cards) : Started(cards) {

    override fun draw(card: Card): State = this

    override fun profit(amount: Amount): Profit = amount.profit(earningRate())

    abstract fun earningRate(): EarningRate

    override fun stay(): State = this
}
