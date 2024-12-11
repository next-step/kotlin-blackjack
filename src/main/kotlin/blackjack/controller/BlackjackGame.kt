package blackjack.controller

import blackjack.domain.card.Deck
import blackjack.domain.player.Dealer
import blackjack.domain.player.Player
import blackjack.view.InputView
import blackjack.view.ResultView

object BlackjackGame {
    fun start() {
        val gameTable = setUp()
        initDeal(gameTable)
        turnStart(gameTable)
        ResultView.printAfterTurn(gameTable)
    }

    private fun setUp(): GameTable {
        val gameTable = GameTable(Deck(), Dealer(), getPlayers())
        ResultView.linebreak()
        return gameTable
    }

    private fun getPlayers(): List<Player> = InputView.inputNames().map { Player(it) }

    private fun initDeal(gameTable: GameTable) {
        gameTable.dealInitCard()
        ResultView.printDealInitCard(gameTable)
    }

    private fun turnStart(gameTable: GameTable) {
        gameTable.playersTurn()
        ResultView.linebreak()
        gameTable.dealerTurn()
    }
}
