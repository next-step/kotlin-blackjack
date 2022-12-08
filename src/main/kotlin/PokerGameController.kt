import model.PokerGame
import ui.InputView.inputPlayerQuestion
import ui.InputView.inputPlayers
import ui.ResultView.resultFinalVictory
import ui.ResultView.resultInitPokerGame
import ui.ResultView.resultPokerGameScore

class PokerGameController {
    fun process() {
        val inputPlayers = inputPlayers()

        val pokerGame = PokerGame(players = inputPlayers)
        resultInitPokerGame(pokerGame)
        inputPlayerQuestion(pokerGame)
        resultPokerGameScore(pokerGame)
        resultFinalVictory(pokerGame)
    }
}

fun main() {
    val pokerGameController = PokerGameController()
    pokerGameController.process()
}
