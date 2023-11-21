import blackjack_dealer.BlackJack
import blackjack_dealer.entity.Card
import blackjack_dealer.entity.CardDeque
import blackjack_dealer.entity.Participants
import blackjack_dealer.ui.InputView
import blackjack_dealer.ui.OutputView

fun main() {
    OutputView.enterParticipantsName()
    val participantsName = InputView.inputParticipantsName()
    val cardDeque = CardDeque.create()

    val participants = Participants.newInstance(participantsName) {
        val initialFirstCard = cardDeque.cardDeque.removeLast()
        val initialSecondCard = cardDeque.cardDeque.removeLast()
        return@newInstance listOf<Card>(initialFirstCard, initialSecondCard)
    }
    OutputView.printParticipantsInformation(participants)

    val canJoinParticipants = participants.getParticipantsCanPlayGame()
    val blackJack = BlackJack(cardDeque = cardDeque, participants = canJoinParticipants)
    blackJack.doGame { InputView.inputGetOneMoreCard()}
}
