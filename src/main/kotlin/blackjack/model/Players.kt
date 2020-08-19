package blackjack.model

class Players(players: List<Player>) {
    private val players = players

    fun size() = players.size

    fun race(player: Player, cardDeck: CardDeck) {
        if (player.available()) {
            player.draw(cardDeck.drawCard())
        }
    }

    fun winner(): Players {
        return Players(players.filter { it.winner() })
    }

    operator fun get(index: Int): Player {
        return players[index]
    }

    override fun toString(): String {
        return players.joinToString { it.name }
    }
}
