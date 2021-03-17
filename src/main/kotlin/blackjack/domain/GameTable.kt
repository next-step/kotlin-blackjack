package blackjack.domain

import blackjack.domain.card.CardDeck
import blackjack.domain.player.Player
import util.toStack

class GameTable(val players: List<Player>) {
    private val cardDeck = CardDeck.shuffle().toStack()

    fun proceedFirstRound() {
        players.forEach { drawAtFirst(it) }
    }

    private fun drawAtFirst(player: Player) {
        repeat(2) { player.draw(cardDeck.pop()) }
    }
}
