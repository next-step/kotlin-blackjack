package domain.player

import domain.card.CardDeck

class Players(private val players: List<Player>) {

    override fun toString(): String {
        return players.joinToString { it.name }
    }

    fun size() = players.size

    fun progress(player: Player, cardDeck: CardDeck) {
        if (player.canDraw()) {
            player.drawCard(cardDeck.draw())
        }
    }

    operator fun get(index: Int): Player {
        return players[index]
    }
}
