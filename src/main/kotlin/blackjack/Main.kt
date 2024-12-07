package blackjack

import blackjack.view.InputView
import blackjack.view.ResultView

private val inputView = InputView()
private val resultView = ResultView()
private val gameController = GameController()

fun main() {
    initializeGame()
    additionalDraw()
    result()
}

private fun initializeGame() {
    val initialDrawCards = gameController.initial(inputView.inputPlayerNames())
    resultView.printInitialDraw(initialDrawCards)
}

private fun additionalDraw() {
    while (gameController.canDrawForAllPlayers()) {
        val drawPlayer = gameController.findDrawPlayer()
        val inputNeedDraw = inputView.inputNeedAdditionalDraw(drawPlayer)
        val drawResult = gameController.draw(drawPlayer.value, inputNeedDraw)
        resultView.printDrawResult(listOf(drawResult))
    }
}

private fun result() {
    val blackJackGameResults = gameController.result()
    resultView.printBlackJackGameResult(blackJackGameResults)
}
