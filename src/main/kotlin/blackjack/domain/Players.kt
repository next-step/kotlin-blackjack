package blackjack.domain

fun <T> List<T>.replace(newValue: T, block: (T) -> Boolean): List<T> {
    return map {
        if (block(it)) newValue else it
    }
}

data class Players(val players: List<Player>) : List<Player> by players {

    fun receiveCardFromDeck(deck: Deck): Players {
        return Players(players.map { it.receiveCard(deck.drawCard()) })
    }

    fun isAllPlayerTurnOff(): Boolean {
        return !players.any() { it.isMyTurn() }
    }

    fun updatePlayerStatus(before: Player, after: Player): Players {
        return Players(players.replace(after) { it == before })
    }

    init {
        require(players.size >= MINIMUM_GAMER)
    }

    companion object {
        private const val MINIMUM_GAMER = 2

        fun createGamers(names: Names): Players {
            return Players(names.names.map { Gamer(it) })
        }
    }
}
