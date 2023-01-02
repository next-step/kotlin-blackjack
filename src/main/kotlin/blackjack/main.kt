package blackjack

import blackjack.domain.Draw
import blackjack.domain.Participant
import blackjack.view.InputView
import blackjack.view.OutputView

fun main() {
    val participant = Participant(InputView.getPerson())
    OutputView.printParticipantInfo(participant)
    participant.getGamerList().forEach {
        do {
            val drawYn = Draw.checkDrawable(InputView.getDrawYn(it))
            it.addCard(drawYn)
            OutputView.printOwnCards(it)
        } while (it.checkDrawable() && drawYn)
    }
    participant.getDealer().addCard(OutputView.printDealerCardAddYn(participant))
    OutputView.printCardInfo(participant)
    participant.checkResult()
    OutputView.printResult(participant)
}
