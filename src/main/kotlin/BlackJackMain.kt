
import blackjack.BlackJackGame
import model.BlackJackGameStatusInfo
import model.Cards
import model.Dealer
import model.GameResultStatus
import view.InputView
import view.ResultView

fun main() {
    val players = InputView.inputPlayers()
    val dealer = Dealer(Cards())
    dealer.shuffle()
    val blackJackGame = BlackJackGame(players, dealer)
    blackJackGame.start()
    ResultView.printPlayerInfo(BlackJackGameStatusInfo(players))
    ResultView.printCardInfo(players)

    players.forEach { it.receiveCard(blackJackGame) }

    val gameResultStatusList = players.map { GameResultStatus(it, players) }
    ResultView.printResult(players)
    ResultView.printWinner(gameResultStatusList)
}
