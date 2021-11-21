package blackjack.state

import blackjack.model.Cards

abstract class Running(cards: Cards) : Started(cards) {

    override fun profit(amount: Double): Double = amount
}
