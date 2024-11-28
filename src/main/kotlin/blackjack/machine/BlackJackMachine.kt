package blackjack.machine

import blackjack.player.Player
import blackjack.view.InputView
import blackjack.view.ResultView

class BlackJackMachine {
    fun play() {
        val (dealer, player) = InputView.inputPlayerNames().map { Player.from(name = it) }
        ResultView.printPlayerName(dealer = dealer, player = player)
        ResultView.printPlayersCardStatus(players = listOf(dealer, player))
    }
}
