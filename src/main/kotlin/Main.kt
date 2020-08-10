import blackjack.domain.BlackjackGame
import blackjack.domain.CardDeck
import blackjack.view.InputView
import blackjack.view.ResultView

fun main() {
    try {
        val playerNames = InputView.getPlayers()
        val blackjackGame = BlackjackGame(playerNames, CardDeck())

        ResultView.showCardDistribution(blackjackGame.players)
        while (!blackjackGame.isEnd) {
            val isHit = InputView.getHitOrStay(blackjackGame.players.currentPlayer)
            ResultView.showPlayerCard(blackjackGame.hitOrStay(isHit))
            blackjackGame.nextTurn()
        }

        ResultView.showResult(blackjackGame.players)
    } catch (e: Exception) {
        println(e.message)
    }
}
