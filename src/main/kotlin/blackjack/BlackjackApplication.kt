package blackjack

import blackjack.view.InputView
import blackjack.view.ResultView

fun main() {
    val inputView = InputView()
    val resultView = ResultView()
    val blackjackGame = BlackjackGame()

    val names = inputView.inputNames()
    resultView.printNames(names)
    val participants = blackjackGame.makeParticipants(names)
    blackjackGame.init(participants)
    resultView.printCards(participants)

    participants.forEach { participant ->
        while (participant.receive) {
            inputView.inputAnswer(participant)
            if (participant.receive) {
                participant.addCard()
                resultView.printCards(listOf(participant))
            }
        }
    }

    resultView.printResult(participants)
}
