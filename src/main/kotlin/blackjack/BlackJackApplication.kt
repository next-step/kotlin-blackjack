package blackjack

import blackjack.strategy.ui.input.ConsoleInputStrategy
import blackjack.strategy.ui.output.ConsoleOutputStrategy
import blackjack.ui.ErrorView
import blackjack.ui.InputView
import blackjack.ui.ResultView

class BlackJackApplication(
    private val inputView: InputView,
    private val resultView: ResultView,
    private val errorView: ErrorView,
) {
    fun run() {
        return Unit
    }
}

fun main() {
    val inputView = InputView(ConsoleInputStrategy, ConsoleOutputStrategy)
    val resultView = ResultView(ConsoleOutputStrategy)
    val errorView = ErrorView(ConsoleOutputStrategy)
    BlackJackApplication(inputView, resultView, errorView).run()
}
