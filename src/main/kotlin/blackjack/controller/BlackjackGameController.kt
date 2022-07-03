package blackjack.controller

import blackjack.domain.BlackjackGame
import blackjack.domain.Deck
import blackjack.domain.participant.Dealer
import blackjack.domain.participant.Participants
import blackjack.domain.participant.Player
import blackjack.view.InputView
import blackjack.view.OutputView

class BlackjackGameController {
    fun run() {
        val blackjackGame = BlackjackGame(
            Deck.createOf(),
            createParticipants(),
        )

        blackjackGame.start()
        OutputView.printPlayersInitCards(blackjackGame.participants)
        playPlayersTurn(blackjackGame)
        playDealerTurn(blackjackGame)

        OutputView.printResult(blackjackGame.participants)
    }

    private fun createParticipants(): Participants {
        return InputView.inputPlayerNames()
            .map { Player(it) }
            .let { Participants(it.plus(Dealer())) }
    }

    private fun playPlayersTurn(blackjackGame: BlackjackGame) {
        while (blackjackGame.isPlaying()) {
            val currentTurnPlayer = blackjackGame.findCurrentTurnPlayer()
            blackjackGame.askDrawToCurrentTurnPlayer(InputView.askDrawCard(currentTurnPlayer.name))
            OutputView.printCurrentPlayerCards(currentTurnPlayer)
        }
    }

    private fun playDealerTurn(blackjackGame: BlackjackGame) {
        if (blackjackGame.isSatisfiedDealerPullOutCondition()) {
            OutputView.printDealerTurn()
        }
    }
}
