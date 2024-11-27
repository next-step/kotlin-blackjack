import view.InputView
import view.ResultView

fun main() {
    val inputView = InputView()
    val blackJackGame = inputView.inputBlackJack()
    val resultView = ResultView()
    resultView.printBlackJackGameResult(blackJackGame.result())
}