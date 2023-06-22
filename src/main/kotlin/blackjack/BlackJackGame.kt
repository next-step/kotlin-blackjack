package blackjack

import blackjack.view.InputView

object BlackJackGame {
    fun startGame() {
        val players = InputView.getPlayers()
        BlackJackController.startRound(players)
    }
}