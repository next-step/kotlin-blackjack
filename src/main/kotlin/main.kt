import service.BlackjackGame
import view.ConsoleInputView
import view.ConsoleOutputView

fun main() {
    val inputHandler = ConsoleInputView()
    val outputHandler = ConsoleOutputView()

    val game = BlackjackGame(inputHandler, outputHandler)
    val playerNames = inputHandler.readPlayerNames()
    playerNames.forEach { game.addPlayer(it) }

    game.startGame()
}
