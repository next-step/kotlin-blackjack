package blackjack

import blackjack.domain.ResultCalculator
import blackjack.view.input.ConsoleInputView
import blackjack.view.result.ConsoleResultView

fun main() {
    BlackjackGameLauncher(ConsoleInputView(), ConsoleResultView(), ResultCalculator()).launch()
}
