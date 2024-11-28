package blackjack

import blackjack.domain.BlackjackGame
import blackjack.domain.Deck
import blackjack.domain.parser.PlayerParser
import blackjack.view.InputView
import blackjack.view.ResultView

class BlackjackRunner {

    fun run() {
        val blackjackGame = BlackjackGame(
            deck = Deck(),
            players = PlayerParser.parse(InputView.showAndGetPlayers()),
        )

        blackjackGame.start()
        ResultView.printStartCards(blackjackGame.players)

        blackjackGame.players.forEach { player ->
            while (InputView.showAndGetHitOrNot(player.name)) {
                blackjackGame.draw(player)
                ResultView.printPlayerCard(player)
            }
        }

        ResultView.printPlayerResult(blackjackGame.players)
    }

}

fun main() {
    BlackjackRunner().run()
}
