package blackjack

import blackjack.domain.BlackjackGame
import blackjack.domain.Deck
import blackjack.domain.parser.PlayerParser
import blackjack.domain.participant.Dealer
import blackjack.domain.participant.Player
import blackjack.view.InputView
import blackjack.view.ResultView

class BlackjackRunner {

    fun run() {
        val blackjackGame = BlackjackGame(
            deck = Deck(),
            dealer = Dealer(),
            players = PlayerParser.parse(InputView.showAndGetPlayers()),
        )

        blackjackGame.start()
        ResultView.printStartCards(blackjackGame.participants)

        drawPlayers(blackjackGame)
        drawDealer(blackjackGame)

        ResultView.printParticipantsResult(blackjackGame.participants)
        ResultView.printGameResult(blackjackGame.getGameResult())
    }

    private fun drawPlayers(blackjackGame: BlackjackGame) {
        blackjackGame.participants.filterIsInstance<Player>().forEach { player ->
            while (player.canReceiveCard() && InputView.showAndGetHitOrNot(player.name)) {
                blackjackGame.draw(player)
                ResultView.printPlayerCard(player)
            }
        }
    }

    private fun drawDealer(blackjackGame: BlackjackGame) {
        val dealer = blackjackGame.participants.filterIsInstance<Dealer>().firstOrNull() ?: return
        while (dealer.canReceiveCard()) {
            blackjackGame.draw(dealer)
            ResultView.printGetCardForDealer()
        }
    }

}

fun main() {
    BlackjackRunner().run()
}
