
import blackjack.BlackJackGame
import model.*
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
        receiveCard(it, blackJackGame)
    }

    val gameResultStatuList = players.map { GameResultStatus(it, players) }
    ResultView.printResult(players)
    ResultView.printWinner(gameResultStatuList)
}

private fun receiveCard(it: AbstractPlayer, blackJackGame: BlackJackGame) {
    if (it is DealerPlayer) {
        receiveCardDealer(it, blackJackGame)
    } else if(it is Player) {
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
    print("딜러는 16이하라 한장의 카드를 더 받았습니다.\n")
    blackJackGame.receiveCard(it)
}
