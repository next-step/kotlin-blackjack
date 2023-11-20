import blackjack_dealer.entity.CardDeque
import blackjack_dealer.entity.Participants
import blackjack_dealer.ui.InputView
import blackjack_dealer.ui.OutputView

fun main() {
    OutputView.enterParticipantsName()
    val participantsName = InputView.inputParticipantsName()
    CardDeque.create()

    val participants = Participants.newInstance(participantsName)
    println(participants)
}
