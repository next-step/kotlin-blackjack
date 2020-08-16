import blackjack.domain.BlackjackGame
import blackjack.domain.CardDeck
import blackjack.domain.Players
import blackjack.view.InputView
import blackjack.view.ResultView

fun main() {
    try {

        var players: Players? = Players.newInstance(InputView.getPlayers())
        while (players == null) {
            players = Players.newInstance(InputView.getPlayers())
        }

        val blackjackGame = BlackjackGame(players, CardDeck())

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
