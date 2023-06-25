package blackjack.domain.player

import blackjack.domain.card.Deck

class Participants(val players: List<Player>) {
    fun drawAll(deck: Deck) {
        players.forEach { it.drawCard(deck) }
    }
}
