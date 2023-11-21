import blackjack_dealer.BlackJack
import blackjack_dealer.CardGenerator
import blackjack_dealer.domain.Dealer
import blackjack_dealer.entity.CardDeque
import blackjack_dealer.entity.Participants
import blackjack_dealer.ui.InputView
import blackjack_dealer.ui.OutputView

fun main() {
    OutputView.enterParticipantsName()
    val participantsName = InputView.inputParticipantsName()
    val cardDeque = CardDeque.create()

    val participants = Participants.newInstance(participantsName) { CardGenerator.generateDoubleCard(cardDeque) }
    val dealer = Dealer.newInstance(CardGenerator.generateDoubleCard(cardDeque))
    OutputView.printDivideCardsToGamer(dealer, participants)

    OutputView.printGamersInformation(dealer, participants)

    val canJoinParticipants = participants.getParticipantsCanPlayGame()
    val blackJack = BlackJack(cardDeque = cardDeque, participants = canJoinParticipants)
    blackJack.doGame { InputView.inputGetOneMoreCard() }

    OutputView.printResult(participants)
}
