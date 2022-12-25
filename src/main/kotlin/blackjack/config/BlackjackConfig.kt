package blackjack.config

import blackjack.controller.BlackjackController
import blackjack.view.BlackjackView
import blackjack.view.console.ConsoleBlackjackView
import blackjack.view.input.BlackjackInputReader
import blackjack.view.input.console.ConsoleBlackjackInputReader

class BlackjackConfig {
    private fun blackjackView(): BlackjackView = ConsoleBlackjackView()

    private fun blackjackInputReader(): BlackjackInputReader =
        ConsoleBlackjackInputReader(blackjackView())

    fun application(): BlackjackController =
        BlackjackController(
            blackjackView = blackjackView(),
            blackjackInputReader = blackjackInputReader()
        )
}
