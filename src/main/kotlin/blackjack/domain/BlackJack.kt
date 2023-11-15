package blackjack.domain

import blackjack.entity.Participant
import blackjack.entity.Participants
import blackjack.ui.OutputView

class BlackJack(
    private val participants: Participants
) {
    fun doBlackJack(canGetCard: (Participant) -> Boolean, input: () -> Boolean): List<String> =
        participants.map { participant ->
            askGetCardToParticipant(canGetCard, participant, input)
            participant.toString()
        }

    private fun askGetCardToParticipant(
        canGetCard: (Participant) -> Boolean,
        participant: Participant,
        input: () -> Boolean
    ) {
        while (canGetCard(participant)) {
            OutputView.printGetMoreOneCard(participant.name)
            if (input().not()) break

            val newCard = CardGenerator.generateCard(GENERATE_SINGLE_CARD)
            participant.cards = participant.cards.copy(cards = participant.cards.cards + newCard)
            OutputView.printNewCards(participant)
        }
    }

    companion object {
        private const val GENERATE_SINGLE_CARD = 1
    }
}
