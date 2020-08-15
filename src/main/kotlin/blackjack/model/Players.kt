package blackjack.model

class Players(players: List<Player>) {
    private val players = players

    fun size() = players.size

    fun findPlayer(index: Int): Player {
        return players[index]
    }

    fun firstTurn(cardDeck: CardDeck) {
        players.forEach {
            it.draw(cardDeck.pickCard())
            it.draw(cardDeck.pickCard())
        }
    }

    fun race(player: Player, cardDeck: CardDeck) {
        if (player.isAvailable) {
            player.draw(cardDeck.pickCard())
        }
    }

    fun winner(): Players {
        return Players(players.filter { it.isWinner })
    }

    override fun toString(): String {
        return players.joinToString { it.name }
    }
}
