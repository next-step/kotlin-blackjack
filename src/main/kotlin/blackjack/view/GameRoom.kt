package blackjack.view

import blackjack.domain.Game

fun main() {
    val playerNames = InputView.inputPlayerNames()
    val game = Game(playerNames, InputView::inpuyWhetherHit)
        .start(ResultView::printStart)
        .hits(ResultView::printPlayerCards)

    ResultView.printResult(game.players)
}
