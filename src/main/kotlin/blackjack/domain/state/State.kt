package blackjack.domain.state

import blackjack.domain.card.Card

interface State {
    val isFinished: Boolean
    fun profit(money: Double): Double
    fun draw(card: Card): State
}
