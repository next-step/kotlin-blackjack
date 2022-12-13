import model.BlackJackGame
import ui.InputView.inputPlayerQuestion
import ui.InputView.inputPlayers
import ui.InputView.inputPlayersBetting
import ui.ResultView.resultBlackJackGameScore
import ui.ResultView.resultInitBlackJackGame
import ui.ResultView.resultProfit

class BlackJackGameController {
    fun process() {
        val inputPlayers = inputPlayers()

        val blackJackGame = BlackJackGame(players = inputPlayers)
        inputPlayersBetting(blackJackGame)
        resultInitBlackJackGame(blackJackGame)
        inputPlayerQuestion(blackJackGame)
        resultBlackJackGameScore(blackJackGame)
        resultProfit(blackJackGame)
    }
}

fun main() {
    val blackJackGameController = BlackJackGameController()
    blackJackGameController.process()
}
