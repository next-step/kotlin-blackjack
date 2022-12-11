import model.BlackJackGame
import ui.InputView.inputPlayerQuestion
import ui.InputView.inputPlayers
import ui.InputView.inputPlayersBetting
import ui.ResultView.resultInitPokerGame
import ui.ResultView.resultPokerGameScore
import ui.ResultView.resultProfit

class PokerGameController {
    fun process() {
        val inputPlayers = inputPlayers()

        val blackJackGame = BlackJackGame(players = inputPlayers)
        inputPlayersBetting(blackJackGame)
        resultInitPokerGame(blackJackGame)
        inputPlayerQuestion(blackJackGame)
        resultPokerGameScore(blackJackGame)
        resultProfit(blackJackGame)
    }
}

fun main() {
    val pokerGameController = PokerGameController()
    pokerGameController.process()
}
