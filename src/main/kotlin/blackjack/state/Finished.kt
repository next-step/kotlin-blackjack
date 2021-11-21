package blackjack.state

import blackjack.model.Card
import blackjack.model.Cards

abstract class Finished(cards: Cards) : Started(cards) {

    override fun draw(card: Card): State = this

    override fun profit(amount: Double): Double = earningRate() * amount

    abstract fun earningRate(): Double

    override fun stay(): State = this
}
