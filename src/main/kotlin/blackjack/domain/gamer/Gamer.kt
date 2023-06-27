package blackjack.domain.gamer

import blackjack.domain.card.Card
import blackjack.domain.card.InitCard
import blackjack.domain.state.GamerState

abstract class Gamer {

    var state = GamerState.wait()
        private set

    fun init(initCard: InitCard) {
        state = state.init(initCard)
    }

    fun hit(card: Card) {
        state = state.hit(card)
    }

    fun hasCard(): Boolean {
        return state.cards.isNotEmpty()
    }

    fun notHasCard(): Boolean {
        return state.cards.isEmpty()
    }

    fun stay() {
        state = state.stay()
    }

    abstract fun canHit(): Boolean
}
