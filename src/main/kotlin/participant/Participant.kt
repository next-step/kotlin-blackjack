package participant

import card.PlayingCard
import state.State

abstract class Participant(val name: String) {
    abstract var state: State

    abstract fun showCardFirst(): List<PlayingCard>

    fun score(): Int {
        return state.hand.score()
    }

    fun stay() {
        state = state.stay()
    }

    fun drawCard(card: PlayingCard) {
        state = state.drawCard(card)
    }
}
