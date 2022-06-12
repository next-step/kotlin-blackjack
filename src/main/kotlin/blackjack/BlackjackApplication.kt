package blackjack

import blackjack.domain.BlackJackGame
import blackjack.domain.Card
import blackjack.domain.CardDeck
import blackjack.domain.Dealer
import blackjack.domain.Participant
import blackjack.view.GameView
import blackjack.view.InputView
import blackjack.view.ResultView

class BlackjackApplication {
    fun playGame() {
        val cardDeck = CardDeck(Card.createDeck())
        val dealer = InputView.createDealer()
        val player = InputView.createParticipants()
        val blackJackGame = BlackJackGame.of(dealer, player, cardDeck)
        GameView.drawFirstCardDistribution(blackJackGame)
        blackJackGame.firstCardDistribution()
        GameView.displayInitialCard(blackJackGame)
        blackJackGame.participantsSortByPlayer.forEach {
            suggestMoreCard(blackJackGame, it)
        }
        val gameResult = blackJackGame.match()
        GameView.displayResult(gameResult)
        ResultView.gameResult(gameResult)
    }

    private fun suggestMoreCard(blackJackGame: BlackJackGame, participant: Participant) {
        var isHit = isHit(participant)
        do {
            if (isHit) {
                blackJackGame.drawTo(participant.name)
                GameView.printDrawResult(participant)
                isHit = isHit(participant)
            }
        } while (isHit)
    }

    private fun isHit(participant: Participant): Boolean {
        val isDealer = participant.isDealer()
        return if (isDealer) {
            (participant as Dealer).isHit()
        } else {
            playerIsHit(participant)
        }
    }

    private fun playerIsHit(participant: Participant): Boolean {
        return if (participant.isBust()) {
            false
        } else {
            InputView.needMoreCard(participant)
        }
    }
}

fun main() {
    BlackjackApplication().playGame()
}
