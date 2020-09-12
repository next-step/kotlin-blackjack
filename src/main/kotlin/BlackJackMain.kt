
import blackjack.BlackJackGame
import game.BlackJackGameStatusInfo
import game.GameResultStatusDealer
import game.GameResultStatusPlayer
import model.Cards
import model.Dealer
import model.DealerPlayer
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

    val dealerPlayer = players.first { it.isDealer() }
    val notDealerPlayers = players.filter { !it.isDealer() }
    val gameResultStatusPlayer = notDealerPlayers.map { GameResultStatusPlayer(it, dealerPlayer as DealerPlayer) }
    val gameResultStatusDealer = GameResultStatusDealer(dealerPlayer, notDealerPlayers)
    ResultView.printResult(players)
    ResultView.printWinner(gameResultStatusDealer, gameResultStatusPlayer)
}
