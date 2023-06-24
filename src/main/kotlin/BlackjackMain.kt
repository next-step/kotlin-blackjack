import controller.BlackjackGameController
import domain.game.BlackjackGame
import view.InputView
import view.ResultView

fun main() {
    val inputView = InputView()
    val resultView = ResultView()
    val blackjackGameController = BlackjackGameController(
        game = BlackjackGame(),
        inputView = inputView,
        resultView = resultView,
    )

    val players = blackjackGameController.initGame()

    players.forEach {
        blackjackGameController.gameStart(
            player = it,
            inputView = inputView,
            resultView = resultView,
        )
    }

    blackjackGameController.printGameResult(players = players)
}
