import blackjack_dealer.ui.InputView
import blackjack_dealer.ui.OutputView

fun main() {
    OutputView.enterParticipantsName()
    val participants = InputView.inputParticipantsName()
    println(participants)
}
