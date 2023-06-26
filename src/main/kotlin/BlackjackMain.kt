import controller.BlackjackGameController
import domain.game.BlackjackGame
import view.InputView
import view.ResultView

fun main() {
    val inputView = InputView()
    val resultView = ResultView()
    val deckSize = BlackjackGame.BLACKJACK_GAME_DECK_SIZE
    val blackjackGameController = BlackjackGameController(
        inputView = inputView,
        resultView = resultView,
    )

    val game = blackjackGameController.initGame(deckSize = deckSize)

    blackjackGameController.gameStart(game)

    blackjackGameController.printGameResult(game)
}
