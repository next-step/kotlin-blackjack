package blackjack.state

import blackjack.model.Card
import blackjack.model.Cards
import blackjack.model.EarningRate

abstract class Finished(cards: Cards) : Started(cards) {

    override fun draw(card: Card): State = this

    abstract fun earningRate(): EarningRate

    override fun stay(): State = this
}
