package blackjack.domain.player

import blackjack.domain.card.Card
import blackjack.domain.state.Running
import blackjack.domain.state.State

abstract class Player(val name: String, state: State) {
    open var state: State = state
        protected set

    open fun draw(card: Card) {
        state = state.draw(card)
    }

    fun stay() {
        state = state.stay()
    }

    fun canDraw() = state is Running
    fun score() = state.score()
}
