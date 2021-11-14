package blackjack

import blackjack.ui.InputView
import global.strategy.ui.input.ConsoleInputStrategy
import global.strategy.ui.output.ConsoleOutputStrategy

class BlackJackApplication {

}

fun main() {
    val inputView = InputView(ConsoleInputStrategy, ConsoleOutputStrategy)
}
