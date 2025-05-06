package participant

import card.PlayingCard
import state.State

class Dealer(override val state: State) : Participant {
    override val name: String = "Dealer"

    override fun drawCards(cards: List<PlayingCard>) {
        state.drawCards(cards)
    }

    override fun showCardFirst(): List<PlayingCard> {
        return listOf(state.hand.cards.first())
    }
}
