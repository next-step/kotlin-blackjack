package blackjack

import blackjack.domain.BlackjackGame
import blackjack.domain.Deck
import blackjack.domain.participant.Dealer
import blackjack.domain.participant.Player
import blackjack.domain.participant.PlayerAction
import blackjack.view.InputView
import blackjack.view.ResultView
import blackjack.view.dto.toDomain

class BlackjackRunner {

    fun run() {
        val blackjackGame = BlackjackGame(
            deck = Deck(),
            dealer = Dealer(),
            players = InputView.showAndGetPlayers()
                .map { it.toDomain() },
        )

        blackjackGame.start()
        ResultView.printStartCards(blackjackGame.participants)

        drawPlayers(blackjackGame)
        drawDealer(blackjackGame)

        ResultView.printParticipantsResult(blackjackGame.participants)
        ResultView.printGameResult(blackjackGame.getGameResult())
    }

    private fun drawPlayers(blackjackGame: BlackjackGame) {
        blackjackGame.participants.filterIsInstance<Player>()
            .forEach { player ->
                while (player.canReceiveCard() && InputView.showAndGetPlayerAction(player.name) == PlayerAction.HIT) {
                    blackjackGame.draw(player)
                    ResultView.printPlayerInformation(player)
                }
            }
    }

    private fun drawDealer(blackjackGame: BlackjackGame) {
        val dealer = blackjackGame.participants.filterIsInstance<Dealer>()
            .firstOrNull() ?: return
        while (dealer.canReceiveCard()) {
            blackjackGame.draw(dealer)
            ResultView.printGetCardForDealer()
        }
    }

}

fun main() {
    BlackjackRunner().run()
}
