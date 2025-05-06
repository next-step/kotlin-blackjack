package participant

import card.PlayingCard
import state.State

abstract class Participant(val name: String) {
    abstract var state: State

    fun drawCard(card: PlayingCard) {
        state = state.drawCard(card)
    }

    abstract fun showCardFirst(): List<PlayingCard>
}
