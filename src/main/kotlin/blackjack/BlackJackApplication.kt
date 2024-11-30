package blackjack

import blackjack.domain.BlackJackGame
import blackjack.view.InputView
import blackjack.view.OutputView

fun main() {
    val inputView = InputView()
    val outputView = OutputView()
    val inputPlayerNames = inputView.inputPlayerNames()
    val blackJackGame = BlackJackGame.createGame(inputPlayerNames)

    outputView.printInitialPlayersCards(blackJackGame.getInitialPlayerCards())

    blackJackGame.players.forEach { player ->
        while (true) {
            val response = inputView.inputMoreCard(player.getName())
            if (blackJackGame.shouldContinue(response)) {
                val (_, newCard) = blackJackGame.askForMoreCards(player)
                newCard?.let { outputView.printSinglePlayerCards(player.getName(), player.displayHand()) }
            } else {
                break
            }
        }
    }

    outputView.printPlayResult(blackJackGame.getPlayerResults())
}
