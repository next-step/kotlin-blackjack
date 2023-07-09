package blackjack

import blackjack.controller.BlackjackController
import blackjack.domain.Dealer
import blackjack.domain.Participants

fun main() {
    val blackjackController = BlackjackController()
    val dealer = Dealer()
    val participants = Participants(listOf(dealer) + blackjackController.inputPlayers())

    blackjackController.drawInitialCards(participants)
    blackjackController.printInitialCards(participants)

    for (participant in participants.getPlayers()) {
        while (blackjackController.drawMoreCard(participant)) {
            continue
        }
    }
    println()

    if (dealer.canDrawMoreCard()) {
        println("딜러는 16이하라 한장의 카드를 더 받았습니다.")
        println()
        blackjackController.drawMoreCard(dealer)
    }

    // 결과를 출력한다.
    blackjackController.printResult(participants)

    // 최종 승패를 출력한다.
    blackjackController.printFinalResult(participants)
}
