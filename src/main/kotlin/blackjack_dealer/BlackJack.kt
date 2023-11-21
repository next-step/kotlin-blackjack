package blackjack_dealer

import blackjack_dealer.entity.CardDeque
import blackjack_dealer.entity.Participants
import blackjack_dealer.ui.OutputView

class BlackJack(
    private val cardDeque: CardDeque,
    private val participants: Participants,
) {
    fun doGame(
        input: () -> Boolean,
    ) {
        participants.forEach { participant ->
            while (true) {
                OutputView.askGetOneMoreCard(participant)
                if (input.invoke()) {
                    participant.drawCard(cardDeque)
                    OutputView.printParticipantInformation(participant)
                    if (participant.canJoinGame().not()) break
                } else {
                    participant.doStand()
                    break
                }
            }
        }
    }
}
