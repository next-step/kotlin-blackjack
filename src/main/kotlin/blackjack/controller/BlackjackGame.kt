package blackjack.controller

import blackjack.domain.player.Players
import blackjack.view.InputView
import blackjack.view.OutputView

class BlackjackGame(
    val inputView: InputView = InputView(),
    val ouputView: OutputView = OutputView(),
) {
    fun play() {
        val players = Players.of(playNameValues = inputView.inputPlayerNames())
    }
}
