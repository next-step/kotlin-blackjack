package next.step.blackjack.controller

import next.step.blackjack.domain.GameBoard
import next.step.blackjack.domain.GameCards
import next.step.racing.view.InputView
import next.step.racing.view.OutputView

fun main() {
    runCatching {
        val gameBoard = GameBoard.of(GameCards.shuffled(), InputView.readPlayers())
        gameBoard.start { players, cardCnt -> OutputView.showStart(players, cardCnt) }
        gameBoard.turn(InputView::readTurn) { player -> OutputView.showPlayerCards(player) }
        gameBoard.finish { players -> OutputView.showResult(players) }
    }.onFailure { e ->
        OutputView.showError(e.message)
        main()
    }
}
