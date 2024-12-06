package blackjack

fun main() {
    val inputView = InputView()
    val blackJackGame = inputView.inputBlackJack()
    val resultView = ResultView()
    resultView.printBlackJackGameResult(blackJackGame.result())
}