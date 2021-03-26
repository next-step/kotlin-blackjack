package blackjack.domain.player

import blackjack.domain.card.state.State

class Dealer(state: State) : Participant(DEALER_NAME, state) {

    override fun toString(): String {
        return "Dealer(name=$name, state=$state)"
    }

    companion object {
        private val DEALER_NAME = Name("딜러")
    }
}
