package blackjack

import blackjack.domain.DrawParticipant
import blackjack.view.InputView
import blackjack.view.ResultView

private val inputView = InputView()
private val resultView = ResultView()
private val gameController = GameController()
private const val ADDITIONAL_DRAW_INPUT = "y"

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
        val drawParticipant = gameController.findDrawParticipant() ?: break
        val inputNeedDraw = inputNeedDraw(drawParticipant)
        val drawResult = gameController.draw(drawParticipant.name.value, inputNeedDraw)
        resultView.printDrawResult(drawResult)
    }
}

private fun inputNeedDraw(
    drawParticipant: DrawParticipant,
): String? = if (!drawParticipant.dealer) {
    inputView.inputNeedAdditionalDraw(drawParticipant.name)
} else {
    ADDITIONAL_DRAW_INPUT
}

private fun result() {
    val blackJackGameResults = gameController.result()
    resultView.printBlackJackGameResults(blackJackGameResults)
}
