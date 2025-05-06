package participant

import card.PlayingCard
import state.State

interface Participant {
    val state: State
    val name: String

    fun drawCards(cards: List<PlayingCard>)

    fun showCardFirst(): List<PlayingCard>
}
