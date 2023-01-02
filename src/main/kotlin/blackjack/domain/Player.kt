package blackjack.domain

import blackjack.domain.card.Card
import blackjack.domain.state.Running
import blackjack.domain.state.State

open class Player(val name: String, state: State) {
    open var state: State = state
        protected set

    open fun draw(card: Card) {
        state = state.draw(card)
    }

    fun profit(money: Double) = state.profit(money)

    fun canDraw() = state is Running

    fun score() = state.score()
}
