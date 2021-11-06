package blackjack

import blackjack.view.input.InputView
import blackjack.view.result.ResultView

class BlackjackGameLauncher(private val inputView: InputView, private val resultView: ResultView) {
    fun launch() {
        val players = inputView.getPlayers()
    }
}
