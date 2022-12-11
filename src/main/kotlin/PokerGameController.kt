import model.BlackJackGame
import ui.InputView.inputPlayerQuestion
import ui.InputView.inputPlayers
import ui.ResultView.resultFinalVictory
import ui.ResultView.resultInitPokerGame
import ui.ResultView.resultPokerGameScore

class PokerGameController {
    fun process() {
        val inputPlayers = inputPlayers()

        val blackJackGame = BlackJackGame(players = inputPlayers)
        resultInitPokerGame(blackJackGame)
        inputPlayerQuestion(blackJackGame)
        resultPokerGameScore(blackJackGame)
        resultFinalVictory(blackJackGame)
    }
}

fun main() {
    val pokerGameController = PokerGameController()
    pokerGameController.process()
}
