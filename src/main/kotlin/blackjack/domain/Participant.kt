package blackjack.domain

import blackjack.entity.Cards
import blackjack.entity.ParticipantState

data class Participant(
    val name: String,
    val cards: Cards,
) {
    var participantState: ParticipantState = calculateParticipantState()
        private set

    private fun calculateParticipantState(): ParticipantState {
        if (participantState is ParticipantState.STAND) return participantState
        return when (cards.sumOfCardNumbers()) {
            in MINIMUM_HIT_NUMBER..MAXIMUM_HIT_NUMBER -> ParticipantState.HIT
            BLACK_JACK -> ParticipantState.BLACKJACK
            else -> ParticipantState.BUST
        }
    }

    fun setParticipantState(state: ParticipantState.STAND) {
        participantState = state
    }

    fun copyNewCards(newCards: Cards): Participant = copy(cards = newCards)

    companion object {
        private const val MINIMUM_HIT_NUMBER = 1
        private const val MAXIMUM_HIT_NUMBER = 20
        private const val BLACK_JACK = 21
    }
}
