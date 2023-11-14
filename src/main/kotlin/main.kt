import blackjack.domain.CardGenerator
import blackjack.entity.Participant
import blackjack.entity.toParticipants
import blackjack.ui.InputView
import blackjack.ui.OutputView

fun main() {
    // PHASE 1
    OutputView.printEnterParticipantsName()
    val participantName = InputView.inputParticipantName()
    OutputView.printParticipantsName(participantName)

    // PHASE 2
    val participants = participantName.split(", ").map { name ->
        Participant(name = name, cards = CardGenerator.generateCard(INITIAL_COUNT_OF_CARD))
    }.toParticipants()
    OutputView.printParticipantsCard(participants)
}

private const val INITIAL_COUNT_OF_CARD = 2
