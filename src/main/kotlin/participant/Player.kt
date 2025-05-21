package participant

import card.PlayingCard
import state.State

class Player(name: String, override var state: State) : Participant(name) {
    override fun showCardFirst(): List<PlayingCard> {
        return state.hand.cards
    }

    override fun wantDraw(): Boolean {
        return true
    }
}
