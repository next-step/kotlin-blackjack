package blackjack.domain

import blackjack.domain.card.Card
import blackjack.domain.state.Running
import blackjack.domain.state.State

open class Player(val name: String, state: State) {
    open var state: State = state
        protected set

    lateinit var bat: Bat

    open fun draw(card: Card) {
        state = state.draw(card)
    }

    fun stay() {
        state = state.stay()
    }

    fun bat(money: Double) {
        bat = Bat(money)
    }

    fun canDraw() = state is Running
    fun score() = state.score()
    fun profit(dealer: Dealer) = bat.profit(state, dealer)
}
