package blackjack.domain

import blackjack.domain.card.Card
import blackjack.domain.state.Hit
import blackjack.domain.state.State

open class Player(val name: String, state: State) {
    open var state: State = state
        protected set

    open fun canDraw(): Boolean {
        return state is Hit
    }

    fun draw(card: Card) {
        state = state.draw(card)
    }

    fun score(): Int {
        return state.hand.score
    }
}
