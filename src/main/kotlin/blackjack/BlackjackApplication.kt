package blackjack

import blackjack.domain.BlackJackGame
import blackjack.domain.Card
import blackjack.domain.CardDeck
import blackjack.domain.Participant
import blackjack.view.GameView
import blackjack.view.InputView
import blackjack.view.ResultView

class BlackjackApplication {
    fun playGame() {
        val cardDeck = CardDeck(Card.createDeck())
        val playerNames = InputView.createParticipants()
        val blackJackGame = BlackJackGame.of(playerNames, cardDeck)
        GameView.drawFirstCardDistribution(blackJackGame)
        blackJackGame.firstCardDistribution()
        GameView.displayInitialCard(blackJackGame)
        blackJackGame.normalPlayer.forEach { participant ->
            suggestMoreCard(blackJackGame, participant)
        }
        drawDealerCard(blackJackGame)

        GameView.displayResult(blackJackGame)
        ResultView.gameResult(blackJackGame)
    }

    private fun suggestMoreCard(blackJackGame: BlackJackGame, participant: Participant) {
        var isHit = InputView.needMoreCard(participant)
        do {
            if (isHit) {
                blackJackGame.drawTo(participant.name)
                GameView.displayPlayerCard(participant)
                isHit = InputView.needMoreCard(participant)
            }
        } while (isHit)
    }

    private fun drawDealerCard(blackJackGame: BlackJackGame) {
        var isHit = blackJackGame.dealerCards.isDealerHit()
        do {
            if (isHit) {
                blackJackGame.drawToDealer()
                GameView.dealerDrawCard()
                isHit = blackJackGame.dealerCards.isDealerHit()
            }
        } while (isHit)
    }
}

fun main() {
    BlackjackApplication().playGame()
}
