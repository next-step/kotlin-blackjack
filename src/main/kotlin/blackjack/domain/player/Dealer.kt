package blackjack.domain.player

import blackjack.domain.card.Score
import blackjack.domain.card.state.State

class Dealer(state: State) : Participant(DEALER_NAME, state) {

    fun isMustTakeCard(): Boolean {
        if (score <= HIT_ROLE) {
            return true
        }
        state.stay()
        return false
    }

    override fun toString(): String {
        return "Dealer(name=$name, state=$state)"
    }

    companion object {
        private val DEALER_NAME = Name("딜러")
        private val HIT_ROLE = Score.of(16)
    }
}
