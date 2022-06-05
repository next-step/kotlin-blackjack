package blackjack

import blackjack.domain.BlackJackGame
import blackjack.domain.Card
import blackjack.domain.CardDeck
import blackjack.view.GameView
import blackjack.view.InputView

class BlackjackApplication {
    fun playGame() {
        val playerNames = InputView.createParticipants()
        val blackJackGame = BlackJackGame.of(playerNames)
        GameView.giveCard(blackJackGame)
        blackJackGame.firstCardDistribution()
        GameView.displayInitialCard(blackJackGame)
        blackJackGame.suggestMoreCardToEachPlayer()

        GameView.displayResult(blackJackGame)
    }
}

fun main() {
    BlackjackApplication().playGame()
}
