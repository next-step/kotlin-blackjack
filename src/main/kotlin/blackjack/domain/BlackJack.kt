package blackjack.domain

import blackjack.entity.Participant
import blackjack.entity.Participants

class BlackJack(
    private val participants: Participants
) {
    fun doBlackJack(
        canGetCard: (Participant) -> Boolean,
        printGetOneMoreCard: (String) -> Unit,
        input: () -> Boolean,
        printNewCard: (Participant) -> Unit,
    ): List<String> =
        participants.map { participant ->
            askGetCardToParticipant(canGetCard, printGetOneMoreCard, participant, input, printNewCard)
            participant.toString()
        }

    private fun askGetCardToParticipant(
        canGetCard: (Participant) -> Boolean,
        printGetOneMoreCard: (String) -> Unit,
        participant: Participant,
        input: () -> Boolean,
        printNewCard: (Participant) -> Unit,
    ) {
        while (canGetCard(participant)) {
            printGetOneMoreCard(participant.name)
            if (input().not()) break

            val newCard = CardGenerator.generateCard(GENERATE_SINGLE_CARD)
            participant.cards = participant.cards.copy(cards = participant.cards.cards + newCard)
            printNewCard(participant)
        }
    }

    companion object {
        private const val GENERATE_SINGLE_CARD = 1
    }
}
