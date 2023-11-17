package blackjack.domain

import blackjack.entity.Participant
import blackjack.entity.ParticipantState

class BlackJack {
    fun doBlackJack(
        participant: Participant,
        printGetOneMoreCard: (String) -> Unit,
        input: () -> Boolean,
        printNewCard: (Participant) -> Unit,
    ): Participant = askGetCardToParticipant(
        printGetOneMoreCard,
        participant,
        input,
        printNewCard
    )

    private fun askGetCardToParticipant(
        printGetOneMoreCard: (String) -> Unit,
        participant: Participant,
        input: () -> Boolean,
        printNewCard: (Participant) -> Unit,
    ): Participant {
        if (participant.participantState is ParticipantState.BUST) return participant
        if (participant.participantState is ParticipantState.BLACKJACK) { return participant }
        if (askWantToGetOneMoreCard(printGetOneMoreCard, participant, input)) return participant

        val newCard = CardGenerator.generateCard(GENERATE_SINGLE_CARD)
        val newCards = participant.cards.addNewCard(newCard)
        val participantWithNewCards = participant.copyNewCards(newCards)
        printNewCard(participantWithNewCards)

        return participantWithNewCards
    }

    private fun askWantToGetOneMoreCard(
        printGetOneMoreCard: (String) -> Unit,
        participant: Participant,
        input: () -> Boolean
    ): Boolean {
        printGetOneMoreCard(participant.name)
        val noMoreCard = input().not()
        if (noMoreCard) participant.setParticipantState(ParticipantState.STAND)
        return noMoreCard
    }

    companion object {
        private const val GENERATE_SINGLE_CARD = 1
    }
}
