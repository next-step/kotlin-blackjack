package blackjack.controller

import blackjack.domain.BlackjackGame
import blackjack.domain.Deck
import blackjack.domain.bettingmoney.BettingMoney
import blackjack.domain.participant.Dealer
import blackjack.domain.participant.Player
import blackjack.domain.participant.Players
import blackjack.view.InputView
import blackjack.view.OutputView

object BlackjackGameController {
    fun run() {
        val blackjackGame = BlackjackGame(
            Deck.createOf(),
            createParticipants(),
            Dealer()
        )

        blackjackGame.start()
        OutputView.printPlayersInitCards(blackjackGame.players)
        playPlayersTurn(blackjackGame)
        playDealerTurn(blackjackGame)

        OutputView.printAllParticipantsCard(blackjackGame.players, blackjackGame.dealer)
        OutputView.printResult(blackjackGame.getGameResults())
    }

    private fun createParticipants(): Players {
        return InputView.inputPlayerNames()
            .map { Player(name = it, bettingMoney = createBettingMoney(it)) }
            .let { Players(it) }
    }

    private fun createBettingMoney(it: String) = BettingMoney(InputView.inputBettingMoney(it))

    private fun playPlayersTurn(blackjackGame: BlackjackGame) {
        while (blackjackGame.isPlayerTurn()) {
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
