package blackjack.domain.participant

import blackjack.domain.card.Cards
import blackjack.domain.state.State

sealed class Participant(
    private val name: ParticipantName,
    private val state: State
) {
    fun name(): String {
        return name.value
    }

    fun cards(): Cards {
        return state.cards()
    }
}
