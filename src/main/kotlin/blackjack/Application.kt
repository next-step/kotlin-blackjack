package blackjack

import blackjack.view.input.ConsoleInputView
import blackjack.view.result.ConsoleResultView

fun main() {
    BlackjackGameLauncher(ConsoleInputView(), ConsoleResultView()).launch()
}
