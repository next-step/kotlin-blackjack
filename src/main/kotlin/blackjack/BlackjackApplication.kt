package blackjack

import blackjack.controller.BlackjackController
import blackjack.domain.Dealer
import blackjack.domain.Participants

fun main() {
    val blackjackController = BlackjackController()
    val participants = Participants(listOf(Dealer()) + blackjackController.inputPlayers())

    blackjackController.drawInitialCards(participants)
    blackjackController.printInitialCards(participants)

    for (participant in participants.toList().filter { it !is Dealer }) {
        while (blackjackController.drawMoreCard(participant)) {
            continue
        }
    }
    println()

    // 결과를 출력한다.
    blackjackController.printResult(participants)
}
