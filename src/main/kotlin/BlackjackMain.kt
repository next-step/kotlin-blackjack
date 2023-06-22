import controller.BlackjackGameController
import domain.game.BlackjackGame
import domain.player.Player
import view.Answer
import view.InputView
import view.ResultView

fun main() {
    val inputView = InputView()
    val resultView = ResultView()

    val playerNames = inputView.getPlayerNames()
    val blackjackGameController = BlackjackGameController(BlackjackGame())

    val players = blackjackGameController.initGame(playerNames)

    resultView.printInitPlayers(players = players)

    players.forEach {
        gameStart(player = it, inputView = inputView, controller = blackjackGameController, resultView = resultView)
    }

    resultView.printGameResult(players = players)
}

private fun gameStart(
    player: Player,
    inputView: InputView,
    resultView: ResultView,
    controller: BlackjackGameController
) {
    while (!controller.isTerminatedPlayer(player)) {
        when (inputView.askDraw(player.name)) {
            Answer.YES -> controller.issueCard(player)
            else -> controller.stopIssueCard(player)
        }

        resultView.printPlayerCards(player)
    }
}
