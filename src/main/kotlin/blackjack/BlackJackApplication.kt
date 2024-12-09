package blackjack

import blackjack.controller.BlackjackGameController
import blackjack.domain.BlackjackGame
import blackjack.domain.Deck
import blackjack.infrastructure.ConsoleBackJackInputAdapter
import blackjack.view.InputView
import blackjack.view.OutputView

fun main() {
    val inputView = InputView()
    val outputView = OutputView()
    val consoleBackJackInputAdapter = ConsoleBackJackInputAdapter(inputView)

    val gameController = BlackjackGameController(consoleBackJackInputAdapter, outputView)

    val playersNames = gameController.getPlayersNames()
    val playerBettingMoneys = gameController.getPlayerBettingMoneys(playersNames)

    val deck = Deck()
    val blackJackGame = BlackjackGame.createGame(playersNames, playerBettingMoneys, deck)

    gameController.announceInitialPlayersCards(blackJackGame)

    gameController.playGame(blackJackGame)

    gameController.announceResult(blackJackGame)
}
