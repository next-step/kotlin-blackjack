package blackjack

import blackjack.domain.CardGenerator
import blackjack.domain.Participant
import blackjack.entity.Cards
import blackjack.entity.ParticipantState

class BlackJack(
    private val cardDeque: Cards,
) {
    fun doBlackJack(
        participant: Participant,
        printGetOneMoreCard: (String) -> Unit,
        input: () -> Boolean,
        printNewCard: (Participant) -> Unit,
    ): Participant {
        if (participant.participantState is ParticipantState.BLACKJACK) {
            return participant
        }
        if (askWantToGetOneMoreCard(printGetOneMoreCard, participant, input)) return participant

        val newCard = CardGenerator.generateCard(GENERATE_SINGLE_CARD, cardDeque)
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
