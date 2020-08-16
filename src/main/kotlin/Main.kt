import blackjack.domain.BlackjackGame
import blackjack.domain.CardDeck
import blackjack.view.InputView
import blackjack.view.ResultView

fun main() {
    try {
        val blackjackGame = BlackjackGame(InputView.getPlayers(), CardDeck())

        ResultView.showCardDistribution(blackjackGame.players)
        while (!blackjackGame.isEnd) {
            val currentPlayer = blackjackGame.players.currentPlayer
            if (blackjackGame.hitOrStay(InputView.getHitOrStay(currentPlayer)) == null) continue
            ResultView.showPlayerCard(currentPlayer)
            blackjackGame.nextTurn()
        }
        blackjackGame.players.calculateResult()
        ResultView.showGameResult(blackjackGame.players)
    } catch (e: Exception) {
        println(e.message)
    }
}
