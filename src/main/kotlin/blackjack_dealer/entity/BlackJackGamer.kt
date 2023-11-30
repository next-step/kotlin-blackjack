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
            doBlackJackForEachParticipant(
                participant,
                askGetOneMoreCard,
                getOneMoreCardInput,
                cardDeque,
                printParticipantInformation
            )
        }
    }

    private fun doBlackJackForEachParticipant(
        participant: Participant,
        askGetOneMoreCard: (Participant) -> Unit,
        getOneMoreCardInput: () -> Boolean,
        cardDeque: CardDeque,
        printParticipantInformation: (Participant) -> Unit
    ) {
        while (participant.canKeepPlayingGame()) {
            askGetOneMoreCard(participant)
            if (drawOrStand(getOneMoreCardInput, participant, cardDeque, printParticipantInformation)) break
        }
    }

    private fun drawOrStand(
        getOneMoreCardInput: () -> Boolean,
        participant: Participant,
        cardDeque: CardDeque,
        printParticipantInformation: (Participant) -> Unit
    ): Boolean {
        if (getOneMoreCardInput.invoke()) {
            participant.drawCard(cardDeque)
            printParticipantInformation(participant)
        } else {
            participant.changeStateToStand()
            return true
        }
        return false
    }

    fun doDealerGame(cardDeque: CardDeque, printGetOneMoreCardForDealer: () -> Unit) {
        while (dealer.canKeepPlayingGame()) {
            printGetOneMoreCardForDealer()
            dealer.drawCard(cardDeque)
        }
    }
}
