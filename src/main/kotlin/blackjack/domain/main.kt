package blackjack.domain

import blackjack.view.ConsoleInputView
import blackjack.view.ConsoleResultView

fun main() {
    val inputView = ConsoleInputView()
    val resultView = ConsoleResultView()

    val blackJackGame = BlackJackGame(inputView, resultView)
    blackJackGame.play()
}
