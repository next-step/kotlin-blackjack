import blackjack_dealer.BlackJack
import blackjack_dealer.domain.Dealer
import blackjack_dealer.entity.BlackJackGamer
import blackjack_dealer.entity.CardDeque
import blackjack_dealer.entity.Participants
import blackjack_dealer.ui.InputView
import blackjack_dealer.ui.OutputView

fun main() {
    OutputView.enterParticipantsName()
    val participantsName = InputView.inputParticipantsName()
    val cardDeque = CardDeque().create()

    val allParticipantWithBetAmount = OutputView.enterBetAmountEachParticipant(participantsName) { InputView.inputBetAmount() }
    val participants = Participants.newInstance(allParticipantWithBetAmount) { cardDeque.generateDoubleCard() }
    val dealer = Dealer.newInstance(cardDeque.generateDoubleCard())
    OutputView.printDivideCardsToGamer(dealer, participants)

    OutputView.printGamersInformation(dealer, participants)

    val canJoinParticipants = participants.getParticipantsCanPlayGame()
    val blackJackGamer = BlackJackGamer(dealer = dealer, participants = canJoinParticipants)
    val blackJack = BlackJack(cardDeque = cardDeque, blackJackGamer = blackJackGamer)
    blackJack.doGame(
        getOneMoreCardInput = { InputView.inputGetOneMoreCard() },
        askGetOneMoreCard = { participant -> OutputView.askGetOneMoreCard(participant) },
        printParticipantInformation = { participant -> OutputView.printParticipantInformation(participant) },
        printGetOneMoreCardForDealer = { OutputView.printGetOneMoreCardForDealer() }
    )

    OutputView.printResult(dealer, participants)

    OutputView.printFinalResultBoard()
    val dealerResult = participants.getParticipantsLoss(dealer)
    OutputView.printFinalDealerResult(dealerResult)
    OutputView.printFinalParticipantsResult(participants, dealer)
}
