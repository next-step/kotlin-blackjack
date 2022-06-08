package blackjack

import blackjack.domain.BlackJackGame
import blackjack.domain.Card
import blackjack.domain.CardDeck
import blackjack.domain.DEALER
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
        var isNeed = InputView.needMoreCard(participant)
        do {
            if (isNeed) {
                blackJackGame.drawTo(participant.name)
                GameView.displayPlayerCard(participant)
                isNeed = InputView.needMoreCard(participant)
            }
        } while (isNeed)
    }

    private fun drawDealerCard(blackJackGame: BlackJackGame) {
        var isDealerNeedCard = blackJackGame.dealerCards.score() <= SCORE_TO_REQUEST_A_CARD_FOR_DEALER
        do {
            if (isDealerNeedCard) {
                blackJackGame.drawTo(DEALER)
                GameView.dealerDrawCard()
                isDealerNeedCard = blackJackGame.dealerCards.score() <= SCORE_TO_REQUEST_A_CARD_FOR_DEALER
            }
        } while (isDealerNeedCard)
    }

    companion object {
        private const val SCORE_TO_REQUEST_A_CARD_FOR_DEALER = 16
    }
}

fun main() {
    BlackjackApplication().playGame()
}
