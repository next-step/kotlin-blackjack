package blackjack.domain.player

import blackjack.domain.card.Deck

fun <T> List<T>.replace(newValue: T, block: (T) -> Boolean): List<T> {
    return map {
        if (block(it)) newValue else it
    }
}

data class Players(val players: List<Player>) : List<Player> by players {

    init {
        require(players.size >= MINIMUM_GAMER)
    }

    fun receiveCardFromDeck(deck: Deck): Players {
        return Players(players.map { it.receiveCard(deck.drawCard()) })
    }

    fun isAllPlayerTurnOff(): Boolean {
        return !players.any() { it.isBurst() }
    }

    fun turnToReady(): Players {
        var updated = players
        for (player in updated) {
            val turnOn = player.turnOn()
            updated = updated.replace(turnOn) { it == player }
        }
        return Players(updated.toList())
    }

    fun receiveCards(player: Player, deck: Deck): PlayerResults {
        val updatedPlayer = player.receiveCard(deck.drawCard())
        return PlayerResults(updatePlayerStatus(player, updatedPlayer), updatedPlayer)
    }

    fun endPlayerTurn(player: Player): Players {
        return Players(updatePlayerStatus(player, player.turnOff()))
    }

    private fun updatePlayerStatus(before: Player, after: Player): Players {
        return Players(players.replace(after) { it == before })
    }

    companion object {
        private const val MINIMUM_GAMER = 2

        fun createGamers(names: Names): Players {
            return Players(names.names.map { Gamer(it) })
        }
    }
}
