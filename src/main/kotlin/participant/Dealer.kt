package participant

import card.PlayingCard
import state.State

class Dealer(name: String = "Dealer", override var state: State) : Participant(name) {
    override fun showCardFirst(): List<PlayingCard> {
        return listOf(state.cards.first())
    }

    override fun wantDraw(): Boolean {
        return score() < 17
    }
}
