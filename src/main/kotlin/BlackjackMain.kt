import controller.BlackjackGameController
import view.InputView
import view.ResultView

fun main() {
    val inputView = InputView()
    val resultView = ResultView()
    val blackjackGameController = BlackjackGameController(
        inputView = inputView,
        resultView = resultView,
    )
    blackjackGameController.gameStart()
}
