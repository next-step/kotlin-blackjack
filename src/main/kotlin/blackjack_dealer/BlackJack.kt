package blackjack_dealer

import blackjack_dealer.domain.Participant
import blackjack_dealer.entity.BlackJackGamer
import blackjack_dealer.entity.CardDeque

class BlackJack(
    private val cardDeque: CardDeque,
    private val blackJackGamer: BlackJackGamer,
) {
    fun doGame(
        getOneMoreCardInput: () -> Boolean,
        askGetOneMoreCard: (Participant) -> Unit,
        printParticipantInformation: (Participant) -> Unit,
        printGetOneMoreCardForDealer: () -> Unit,
    ) {
        blackJackGamer.doParticipantsGame(
            getOneMoreCardInput = getOneMoreCardInput,
            cardDeque = cardDeque,
            askGetOneMoreCard = askGetOneMoreCard,
            printParticipantInformation = printParticipantInformation
        )
        blackJackGamer.doDealerGame(cardDeque, printGetOneMoreCardForDealer)
    }
}
