package blackjack

import blackjack.view.InputView

object BlackJackGame {
    fun startGame() {
        val players = InputView.getPlayers()
        val blackJackTable = BlackJackTable(players)
        blackJackTable.startRound()
        players.forEach(blackJackTable::playEachTurn)
        blackJackTable.endRound()
    }
}
