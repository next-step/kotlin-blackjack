package blackjack_dealer

import blackjack_dealer.domain.Dealer
import blackjack_dealer.entity.CardDeque
import blackjack_dealer.entity.Participants
import blackjack_dealer.ui.OutputView

class BlackJack(
    private val cardDeque: CardDeque,
    private val dealer: Dealer,
    private val participants: Participants,
) {
    fun doGame(
        getOneMoreCardInput: () -> Boolean,
    ) {
        participants.forEach { participant ->
            while (true) {
                OutputView.askGetOneMoreCard(participant)
                if (getOneMoreCardInput.invoke()) {
                    participant.drawCard(cardDeque)
                    OutputView.printParticipantInformation(participant)
                    if (participant.canKeepPlayingGame().not()) break
                } else {
                    participant.changeStateToStand()
                    break
                }
            }
        }
        while (dealer.canKeepPlayingGame()) {
            OutputView.printGetOneMoreCardForDealer()
            dealer.drawCard(cardDeque)
        }
    }
}
