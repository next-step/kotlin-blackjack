package participant

import card.PlayingCard
import state.State

abstract class Participant(val name: String) {
    abstract var state: State

    fun drawCards(cards: List<PlayingCard>) {
        state = state.drawCards(cards)
    }

    abstract fun showCardFirst(): List<PlayingCard>
}
