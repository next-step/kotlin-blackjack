package blackjack

import blackjack.domain.BlackJackGame
import blackjack.domain.Card
import blackjack.domain.CardDeck
import blackjack.view.GameView
import blackjack.view.InputView

class BlackjackApplication {
    fun playGame() {
        val cardDeck = CardDeck(Card.createDeck())
        val playerNames = InputView.createParticipants()
        val blackJackGame = BlackJackGame.of(playerNames, cardDeck)
        GameView.giveCard(blackJackGame)
        blackJackGame.firstCardDistribution()
        GameView.displayInitialCard(blackJackGame)
        blackJackGame.players.forEach {
            while (InputView.needMoreCard(it)) {
                it.addCard()
                GameView.displayPlayerCard(it)
            }
        }

        GameView.displayResult(blackJackGame)
    }
}

fun main() {
    BlackjackApplication().playGame()
}
