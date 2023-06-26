package blackjack.controller

import blackjack.domain.Answer
import blackjack.domain.BlackjackGame
import blackjack.view.InputView
import blackjack.view.OutputView

fun main() {
    val inputUsers = InputView.inputPlayers()
    val printFirstDeal = OutputView.printFirstDeal(inputUsers)

    val game = BlackjackGame(inputUsers)
    game.getPlayers().forEach {
        OutputView.printPlayerCards(it)
    }

    println()
    game.getPlayers().forEach {
        do {
            val answer = InputView.inputHitOrStay(it)
            game.deal(answer, it)
        } while (answer == Answer.HIT)
        OutputView.printPlayerCards(it)
    }
}
