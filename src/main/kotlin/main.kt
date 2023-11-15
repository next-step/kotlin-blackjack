import blackjack.domain.BlackJack
import blackjack.entity.participantsFromNames
import blackjack.ui.InputView
import blackjack.ui.OutputView

fun main() {
    // PHASE 1
    OutputView.printEnterParticipantsName()
    val participantName = InputView.inputParticipantName()
    OutputView.printParticipantsName(participantName)

    // PHASE 2
    val participants = participantName.participantsFromNames()
    OutputView.printParticipantsCard(participants)

    // PHASE 3
    val blackJack = BlackJack(participants)
    val result = blackJack.doBlackJack(
        canGetCard = { participant -> participant.canGetCard },
        input = { InputView.inputGetMoreCard() }
    )
    OutputView.printResult(result)
}
