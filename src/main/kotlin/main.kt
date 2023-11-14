import blackjack.domain.CardGenerator
import blackjack.entity.Cards
import blackjack.entity.Participant
import blackjack.entity.Participants
import blackjack.ui.InputView
import blackjack.ui.OutputView

fun main() {
    // PHASE 1
    OutputView.printEnterParticipantsName()
    val participantName = InputView.inputParticipantName()
    OutputView.printParticipantsName(participantName)

    // PHASE 2
    OutputView.printParticipantsCard(
        Participants(
            listOf(
                Participant(
                    "pita", Cards(listOf(CardGenerator.generateCard(), CardGenerator.generateCard()))
                ),
                Participant(
                    "haero", Cards(listOf(CardGenerator.generateCard(), CardGenerator.generateCard()))
                )
            )
        )
    )
}
