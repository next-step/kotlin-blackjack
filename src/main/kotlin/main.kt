import blackjack_dealer.BlackJack
import blackjack_dealer.BlackJackResultBoard
import blackjack_dealer.CardGenerator
import blackjack_dealer.domain.Dealer
import blackjack_dealer.entity.BlackJackGamer
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
    val blackJackGamer = BlackJackGamer(dealer = dealer, participants = canJoinParticipants)
    val blackJack = BlackJack(cardDeque = cardDeque, blackJackGamer = blackJackGamer)
    blackJack.doGame { InputView.inputGetOneMoreCard() }

    OutputView.printResult(dealer, participants)

    OutputView.printFinalResultBoard()
    val totalResult = BlackJackResultBoard.getBlackJackResult(dealer, participants)
    OutputView.printFinalDealerResult(totalResult.dealerResult)
    OutputView.printFinalParticipantsResult(totalResult.participantsResult)
}
