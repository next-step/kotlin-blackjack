package participant

import card.PlayingCard
import state.State

class Player(override val name: String, override val state: State) : Participant {
    override fun drawCards(cards: List<PlayingCard>) {
        state.drawCards(cards)
    }

    override fun showCardFirst(): List<PlayingCard> {
        return state.hand.cards
    }
}
