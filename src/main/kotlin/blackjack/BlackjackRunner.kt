package blackjack

import blackjack.domain.BlackjackGame
import blackjack.domain.Deck
import blackjack.domain.participant.Dealer
import blackjack.domain.participant.PlayerAction
import blackjack.domain.result.GameResultJudge
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
            gameResultJudge = GameResultJudge(),
        )

        blackjackGame.start()
        ResultView.printStartCards(blackjackGame.dealer, blackjackGame.players)

        drawPlayers(blackjackGame)
        drawDealer(blackjackGame)

        ResultView.printParticipantsResult(blackjackGame.dealer, blackjackGame.players)
        ResultView.printGameResult(blackjackGame.judgeGame())
    }

    private fun drawPlayers(blackjackGame: BlackjackGame) {
        blackjackGame.players.forEach { player ->
            while (player.canReceiveCard() && InputView.showAndGetPlayerAction(player.name) == PlayerAction.HIT) {
                blackjackGame.draw(player)
                ResultView.printPlayerInformation(player)
            }
        }
    }

    private fun drawDealer(blackjackGame: BlackjackGame) {
        while (blackjackGame.dealer.canReceiveCard()) {
            blackjackGame.draw(blackjackGame.dealer)
            ResultView.printGetCardForDealer()
        }
    }

}

fun main() {
    BlackjackRunner().run()
}
