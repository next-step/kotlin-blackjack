package blackjack.domain.player

import blackjack.domain.card.Deck

class Participants(val players: List<PlayerImpl>) {
    fun drawAll(deck: Deck) {
        players.forEach { it.drawCard(deck) }
    }
}
