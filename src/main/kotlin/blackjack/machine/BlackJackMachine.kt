package blackjack.machine

import blackjack.player.Player
import blackjack.player.Players
import blackjack.view.InputView
import blackjack.view.ResultView

class BlackJackMachine {
    fun play() {
        val players = Players(players = InputView.inputPlayerNames().map { Player.from(name = it) })
        ResultView.printPlayersName(players = players)
        ResultView.printPlayersCardStatus(players = players)
    }
}
