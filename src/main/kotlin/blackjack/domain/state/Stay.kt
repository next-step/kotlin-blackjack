package blackjack.domain.state

import blackjack.domain.card.Card

class Stay : Finished() {
    override fun profit(money: Double): Double  {
        return money * 1
    }
}