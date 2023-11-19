package blackjack.domain

import blackjack.entity.ParticipantCards
import blackjack.entity.ParticipantState
import blackjack.entity.toCards

data class Participant(
    val name: String,
    val participantCards: ParticipantCards,
) {
    var participantState: ParticipantState = calculateParticipantState()
        private set

    private fun calculateParticipantState(): ParticipantState {
        if (participantState is ParticipantState.STAND) return participantState
        return when (participantCards.getCurrentScore()) {
            in MINIMUM_HIT_NUMBER..MAXIMUM_HIT_NUMBER -> ParticipantState.HIT
            BLACK_JACK -> ParticipantState.BLACKJACK
            else -> ParticipantState.BUST
        }
    }

    private fun setParticipantState(state: ParticipantState.STAND) {
        participantState = state
    }

    fun drawCard(card: ParticipantCards): Participant = copy(participantCards = (participantCards + card).toCards())

    fun askWantToGetOneMoreCard(
        input: () -> Boolean
    ): Boolean {
        val getMoreCard = input()
        if (getMoreCard.not()) setParticipantState(ParticipantState.STAND)
        return getMoreCard
    }

    companion object {
        private const val MINIMUM_HIT_NUMBER = 1
        private const val MAXIMUM_HIT_NUMBER = 20
        private const val BLACK_JACK = 21
    }
}
