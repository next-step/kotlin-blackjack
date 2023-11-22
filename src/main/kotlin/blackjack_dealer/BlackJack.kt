package blackjack_dealer

import blackjack_dealer.entity.BlackJackGamer
import blackjack_dealer.entity.CardDeque
import blackjack_dealer.ui.OutputView

class BlackJack(
    private val cardDeque: CardDeque,
    private val blackJackGamer: BlackJackGamer,
) {
    fun doGame(
        getOneMoreCardInput: () -> Boolean,
    ) {
        blackJackGamer.participants.forEach { participant ->
            while (participant.canKeepPlayingGame()) {
                OutputView.askGetOneMoreCard(participant)
                if (getOneMoreCardInput.invoke()) {
                    participant.drawCard(cardDeque)
                    OutputView.printParticipantInformation(participant)
                } else {
                    participant.changeStateToStand()
                    break
                }
            }
        }
        while (blackJackGamer.dealer.canKeepPlayingGame()) {
            OutputView.printGetOneMoreCardForDealer()
            blackJackGamer.dealer.drawCard(cardDeque)
        }
    }
}
