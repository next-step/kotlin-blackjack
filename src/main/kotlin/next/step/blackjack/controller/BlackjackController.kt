package next.step.blackjack.controller

import next.step.blackjack.domain.game.GameBoard
import next.step.racing.view.InputView
import next.step.racing.view.OutputView

fun main() {
    runCatching {
        val gameBoard = GameBoard.start(InputView.readPlayerNames()) { dealer, players, initCardCnt ->
            OutputView.showStart(dealer, players, initCardCnt)
        }
        gameBoard.playersTurn(InputView::readTurn) { player -> OutputView.showPlayerCards(player) }
        gameBoard.dealerTurn { OutputView.showDealerHit(it) }
        gameBoard.finish { dealer, players -> OutputView.showResult(dealer, players) }
    }.onFailure { e ->
        OutputView.showError(e.message)
        main()
    }
}
