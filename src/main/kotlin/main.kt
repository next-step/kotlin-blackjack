import blackjack.ui.InputView
import blackjack.ui.OutputView

fun main() {
    // PHASE 1
    OutputView.printEnterParticipantsName()
    val participants = InputView.inputParticipantName()
    OutputView.printParticipantsName(participants)
}
