package next.step.blackjack.controller

import next.step.blackjack.domain.game.GameBoard
import next.step.blackjack.view.InputView
import next.step.blackjack.view.OutputView

fun main() {
    runCatching {
        val gameBoard =
            GameBoard.start(InputView.readPlayerNames(), InputView::readBetting) { dealer, players, initCardCnt ->
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
