package blackjack

import blackjack.domain.BlackJackGame
import blackjack.domain.Card
import blackjack.domain.CardDeck
import blackjack.domain.Participant
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
        blackJackGame.players.forEach { participant ->
            suggestMoreCard(blackJackGame, participant)
        }

        GameView.displayResult(blackJackGame)
    }

    private fun suggestMoreCard(blackJackGame: BlackJackGame, participant: Participant) {
        var isNeed = InputView.needMoreCard(participant)
        do if (isNeed) {
            blackJackGame.drawTo(participant)
            GameView.displayPlayerCard(participant)
            isNeed = InputView.needMoreCard(participant)
        }
        while (isNeed)
    }
}

fun main() {
    BlackjackApplication().playGame()
}
