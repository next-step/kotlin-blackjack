package blackjack

import blackjack.domain.game.Game
import blackjack.view.InputView
import blackjack.view.InputView.inputPlayers
import blackjack.view.PlayerView
import blackjack.view.ResultView

fun main() {
    val game = Game(inputPlayers())
    game.start(
        printFirstTurn = PlayerView::printFirstTurn,
        printPlayerInfo = PlayerView::printPlayerInfo,
        decideHitDecision = InputView::decidePlayerHitDecision,
        printResult = ResultView::printResult
    )
}
