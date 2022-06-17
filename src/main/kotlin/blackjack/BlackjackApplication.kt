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
        val dealer = InputView.createDealer()
        val player = InputView.createParticipants()
        val blackJackGame = BlackJackGame.of(dealer, player, cardDeck)
        GameView.drawFirstCardDistribution(blackJackGame)
        blackJackGame.firstCardDistribution()
        GameView.displayInitialCard(blackJackGame)
        blackJackGame.participantsSortByPlayer.forEach {
            suggestMoreCard(blackJackGame, it)
        }
        GameView.displayResult(blackJackGame)
        ResultView.gameResult(blackJackGame)
    }

    private fun suggestMoreCard(blackJackGame: BlackJackGame, participant: Participant) {
        var isHit = participant.canDrawable()
        do {
            if (isHit) {
                blackJackGame.drawTo(participant.name)
                GameView.printDrawResult(participant)
                isHit = participant.canDrawable()
            }
        } while (isHit)
    }
}

fun main() {
    BlackjackApplication().playGame()
}
