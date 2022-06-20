package blackjack

import blackjack.view.ConsoleInputView
import blackjack.view.ConsoleResultView

fun main() {
    BlackjackApplication.run(ConsoleInputView, ConsoleResultView)
}
