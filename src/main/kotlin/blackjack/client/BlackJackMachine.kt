package blackjack.client

import blackjack.player.Player
import blackjack.view.InputView
import blackjack.view.ResultView

class BlackJackMachine {
    fun play() {
        val (dealer, player) = InputView.inputPlayerNames().map { Player.from(name = it) }
        ResultView.printPlayerName(dealer = dealer, player = player)
    }
}
