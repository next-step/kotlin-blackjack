package blackjack_dealer.entity

import blackjack_dealer.domain.Dealer
import blackjack_dealer.domain.Participant

data class BlackJackGamer(
    val dealer: Dealer,
    val participants: Participants,
) {
    fun doParticipantsGame(
        getOneMoreCardInput: () -> Boolean,
        cardDeque: CardDeque,
        askGetOneMoreCard: (Participant) -> Unit,
        printParticipantInformation: (Participant) -> Unit,
    ) {
        participants.forEach { participant ->
            while (participant.canKeepPlayingGame()) {
                askGetOneMoreCard(participant)
                if (getOneMoreCardInput.invoke()) {
                    participant.drawCard(cardDeque)
                    printParticipantInformation(participant)
                } else {
                    participant.changeStateToStand()
                    break
                }
            }
        }
    }

    fun doDealerGame(cardDeque: CardDeque, printGetOneMoreCardForDealer: () -> Unit) {
        while (dealer.canKeepPlayingGame()) {
            printGetOneMoreCardForDealer()
            dealer.drawCard(cardDeque)
        }
    }
}
