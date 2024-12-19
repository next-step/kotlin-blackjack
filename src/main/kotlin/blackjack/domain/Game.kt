package blackjack.domain

import blackjack.domain.player.Dealer
import blackjack.domain.player.Participant
import blackjack.domain.player.Players

data class Game(
    val players: Players,
    val drawer: Deck,
    val dealer: Dealer,
) {
    fun startGame(
        onPrintResultCallback: ((Participant) -> Unit),
        onTurnStarted: (Participant) -> String,
    ) {
        drawer.fillDeck(Card.cards)

        players.initTurns(onPrintResultCallback)
        dealer.initTurn(onPrintResultCallback)

        players.startTurns(onTurnStarted, onPrintResultCallback)
        dealer.startTurn(onTurnStarted, onPrintResultCallback)
    }

    companion object {
        fun createGame(
            players: Players,
            cardDeck: Deck = CardDeck,
        ): Game {
            return Game(players, cardDeck, Dealer())
        }
    }
}
