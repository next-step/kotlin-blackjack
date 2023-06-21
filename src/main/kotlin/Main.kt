import blackjack.BlackjackGame
import blackjack.io.InputView
import blackjack.io.ResultView

fun main() {
    val users = InputView.getUsers()
    val game = BlackjackGame(users)
    ResultView.printUsersDeck(game.users)

    game.dealCards()
    ResultView.printUsersResult(game.users)
}
