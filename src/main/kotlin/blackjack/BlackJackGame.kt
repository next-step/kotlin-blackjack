package blackjack

import blackjack.view.InputView

object BlackJackGame {
    fun startGame() {
        val players = InputView.getPlayers()
        val blackJackTable = BlackJackTable(players)
        blackJackTable.beginRound()
        blackJackTable.executePlayerTurns(players)
        blackJackTable.checkScoreBoard()
        blackJackTable.endRound()
    }
}
