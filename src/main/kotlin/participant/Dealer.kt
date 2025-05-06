package participant

import card.PlayingCard
import state.State

class Dealer(name: String = "Dealer", override var state: State) : Participant(name) {
    override fun showCardFirst(): List<PlayingCard> {
        return listOf(state.hand.cards.first())
    }
}
