package blackjack

import blackjack.domain.Draw
import blackjack.domain.Participant
import blackjack.view.InputView
import blackjack.view.OutputView

fun main() {
    val participant = Participant(InputView.getPerson())
    OutputView.printParticipantInfo(participant)
    participant.persons.forEach {
        do {
            val drawYn = InputView.getDrawYn(it)
            it.ownCards.addCard()
            OutputView.printOwnCards(it)
        } while (Draw.checkDrawable(it.ownCards) && Draw.checkDrawable(drawYn))
    }
    OutputView.printResult(participant)
}
