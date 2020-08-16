package blackjack.model

import blackjack.model.card.CardDeck
import blackjack.model.player.Players

class BlackJackGame(private val players: Players) {
    private val cardDeck = CardDeck()

    fun firstTurn() {
        players.drawCard(cardDeck)
        players.drawCard(cardDeck)
    }

    fun progressTurn() {
        players.progressTurn(cardDeck)
    }

    fun checkWinOrLose() {
        players.checkWinOrLose()
    }
}
