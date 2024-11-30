package blackjack.machine

import blackjack.player.Player
import blackjack.view.InputView
import blackjack.view.ResultView

class BlackJackMachine {
    fun play() {
        val (player1, player2) = InputView.inputPlayerNames().map { Player.from(name = it) }
        ResultView.printPlayerName(player1 = player1, player2 = player2)
        ResultView.printPlayersCardStatus(players = listOf(player1, player2))
    }
}
