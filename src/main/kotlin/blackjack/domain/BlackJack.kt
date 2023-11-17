package blackjack.domain

import blackjack.entity.Participant

class BlackJack {
    fun doBlackJack(
        canGetCard: Boolean,
        participant: Participant,
        printGetOneMoreCard: (String) -> Unit,
        input: () -> Boolean,
        printNewCard: (Participant) -> Unit,
    ): Participant = askGetCardToParticipant(
        canGetCard,
        printGetOneMoreCard,
        participant,
        input,
        printNewCard
    )

    private fun askGetCardToParticipant(
        canGetCard: Boolean,
        printGetOneMoreCard: (String) -> Unit,
        participant: Participant,
        input: () -> Boolean,
        printNewCard: (Participant) -> Unit,
    ): Participant {
        if (canGetCard.not()) return participant
        if (askWantToGetOneMoreCard(printGetOneMoreCard, participant, input)) return participant

        val newCard = CardGenerator.generateCard(GENERATE_SINGLE_CARD)
        val newCards = participant.cards.addNewCard(newCard)
        val participantWithNewCards = participant.copyNewCards(newCards)
        printNewCard(participantWithNewCards)

        return askGetCardToParticipant(
            participantWithNewCards.canGetCard,
            printGetOneMoreCard,
            participantWithNewCards,
            input,
            printNewCard
        )
    }

    private fun askWantToGetOneMoreCard(
        printGetOneMoreCard: (String) -> Unit,
        participant: Participant,
        input: () -> Boolean
    ): Boolean {
        printGetOneMoreCard(participant.name)
        return input().not()
    }

    companion object {
        private const val GENERATE_SINGLE_CARD = 1
    }
}
