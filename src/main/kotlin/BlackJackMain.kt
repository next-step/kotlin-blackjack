
import blackjack.BlackJackGame
import model.BlackJackGameStatusInfo
import model.Cards
import model.Dealer
import view.InputView
import view.ResultView

fun main() {
    val players = InputView.inputPlayers()
    val dealer = Dealer(Cards())
    val blackJackGame = BlackJackGame(players, dealer)
    blackJackGame.start()
    ResultView.printPlayerInfo(BlackJackGameStatusInfo(players))
    ResultView.printCardInfo(players)

    players.forEach {
        while (it.isReceiveCard(InputView.receiveCard(it))) {
            blackJackGame.receiveCard(it)
            ResultView.printCardCardReceive(it)
        }
    }
    ResultView.printResult(players)
}
