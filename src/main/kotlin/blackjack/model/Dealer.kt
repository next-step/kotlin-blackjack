package blackjack.model

import blackjack.state.Ready
import blackjack.state.State

class Dealer private constructor(name: Name, state: State) : Gamer(name, state) {

    override fun copy(name: Name, state: State): Gamer = Dealer(name, state)

    fun shouldDraw(): Boolean = score <= RECEIVE_CARD_LIMIT

    companion object {
        private const val RECEIVE_CARD_LIMIT = 16

        fun ready(name: Name): Dealer = Dealer(name, Ready())
    }
}
