
import blackjack.BlackJackGame
import model.*
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

    players.forEach { receiveCard(it, blackJackGame) }

    val gameResultStatusList = players.map { GameResultStatus(it, players) }
    ResultView.printResult(players)
    ResultView.printWinner(gameResultStatusList)
}

private fun receiveCard(it: AbstractPlayer, blackJackGame: BlackJackGame) {
    if (it is DealerPlayer) {
        receiveCardDealer(it, blackJackGame)
    } else if (it is Player) {
        receiveCardPlayer(it, blackJackGame)
    }
}

private fun receiveCardPlayer(it: Player, blackJackGame: BlackJackGame) {
    while (it.isReceiveCard(InputView.receiveCard(it))) {
        blackJackGame.receiveCard(it)
        ResultView.printCardCardReceive(it)
    }
}

private fun receiveCardDealer(it: DealerPlayer, blackJackGame: BlackJackGame) {
    if (!it.isAbleReceiveCard()) {
        return
    }
    ResultView.printReceiveCardDealer()
    blackJackGame.receiveCard(it)
}
