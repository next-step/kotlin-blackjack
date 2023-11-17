import blackjack.domain.BlackJack
import blackjack.entity.participantsFromNames
import blackjack.ui.InputView
import blackjack.ui.OutputView
import blackjack.ui.print

fun main() {
    // PHASE 1
    OutputView.printEnterParticipantsName()
    val participantName = InputView.inputParticipantName()
    OutputView.printParticipantsName(participantName)

    // PHASE 2
    val participants = participantName.participantsFromNames()
    OutputView.printParticipantsCard(participants)

    // PHASE 3
    val blackJack = BlackJack()
    val result = participants.map { participant ->
        blackJack.doBlackJack(
            canGetCard = participant.canGetCard,
            participant = participant,
            printGetOneMoreCard = { OutputView.printGetOneMoreCard(it) },
            input = { InputView.inputGetMoreCard() },
            printNewCard = { OutputView.printNewCards(it) }
        ).print()
    }
    OutputView.printResult(result)
}
