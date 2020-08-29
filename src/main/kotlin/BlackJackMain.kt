
import blackjack.BlackJackGame
import model.Dealer
import model.PlayInfo
import view.InputView
import view.ResultView

fun main() {
    val inputView = InputView()
    val players = inputView.inputPlayers()
    val dealer = Dealer()
    val blackJackGame = BlackJackGame(players, dealer)
    blackJackGame.start()
    ResultView.printPlayerInfo(PlayInfo(players))
    ResultView.printCardInfo(players)

    players.forEach {
        while (it.isReceiveCard(inputView.receiveCard(it))) {
            blackJackGame.receiveCard(it)
            ResultView.printCardCardReceive(it)
        }
    }
    ResultView.printResult(players)
}
