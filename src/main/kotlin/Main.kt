import blackjack.domain.BlackjackGame
import blackjack.domain.Players
import blackjack.view.InputView
import blackjack.view.ResultView

fun main() {

    try {
        val players: Players = InputView.getPlayers()
        val blackjackGame = BlackjackGame(players, InputView.getBetMoneyMap(players.players))
        blackjackGame.startGame()

        ResultView.showCardDistribution(blackjackGame.players)

        while (!blackjackGame.isEnd) {
            val currentPlayer = blackjackGame.players.currentPlayer
            if (blackjackGame.hitOrStay(InputView.getHitOrStay(currentPlayer)) == null) continue
            ResultView.showPlayerCard(currentPlayer)
            blackjackGame.nextTurn()
        }

        ResultView.showGameResultWithBetMoney(blackjackGame)
    } catch (e: Exception) {
        ResultView.showErrorMessage(e)
    }
}
