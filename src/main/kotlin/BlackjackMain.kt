import controller.BlackjackGameController
import domain.card.util.DeckGenerator
import domain.game.BlackjackGame
import view.InputView
import view.ResultView

fun main() {
    val inputView = InputView()
    val resultView = ResultView()
    val deck = DeckGenerator.makeDeck(BlackjackGame.BLACKJACK_GAME_DECK_SIZE)
    val blackjackGameController = BlackjackGameController(
        game = BlackjackGame(deck),
        inputView = inputView,
        resultView = resultView,
    )

    blackjackGameController.initGame()

    blackjackGameController.gameStart()

    blackjackGameController.printGameResult()
}
