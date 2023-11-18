package blackjack

import blackjack.model.GameManager
import blackjack.view.InputView
import blackjack.view.OutputView

class BlackJack {
    fun start() {
        val playerNames = InputView.getPlayerName()
        val manager = GameManager(playerNames)
        OutputView.renderPlayers(manager.players)
        manager.playGame()
        OutputView.renderResult(manager.finishGame())
    }
}
