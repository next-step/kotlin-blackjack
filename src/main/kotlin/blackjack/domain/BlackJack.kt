package blackjack.domain

import blackjack.entity.Participant
import blackjack.entity.Participants
import blackjack.ui.OutputView

class BlackJack(
    private val participants: Participants
) {
    fun doBlackJack(canGetCard: (Participant) -> Boolean, input: () -> Boolean) = participants.map { participant ->
        while (canGetCard(participant)) {
            OutputView.printGetMoreOneCard(participant.name)
            if (input().not()) break

            val newCard = CardGenerator.generateCard(1)
            participant.cards = participant.cards.copy(cards = participant.cards.cards + newCard)
            OutputView.printNewCards(participant)
        }
        participant.toString()
    }
}
