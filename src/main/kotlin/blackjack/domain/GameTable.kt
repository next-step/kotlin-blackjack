package blackjack.domain

import blackjack.domain.card.CardDeck
import blackjack.domain.player.Players
import util.toStack

class GameTable(val players: Players) {
    private val cardDeck = CardDeck.shuffle().toStack()

    fun proceedFirstRound() {
        players.draw(cardDeck, FIRST_DRAW_COUNT)
    }

    fun proceedRemainingRound() {
        players.draw(cardDeck)
    }

    companion object {
        private const val FIRST_DRAW_COUNT = 2
    }
}
