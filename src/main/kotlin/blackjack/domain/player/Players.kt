package blackjack.domain.player

import blackjack.application.Game
import blackjack.domain.card.Deck
import blackjack.domain.game.Turn

fun <T> List<T>.replace(newValue: T, block: (T) -> Boolean): List<T> {
    return map {
        if (block(it)) newValue else it
    }
}

data class Players(val players: List<Player>) : List<Player> by players {

    init {
        require(players.size >= MINIMUM_GAMER)
    }

    fun startInitPhase(deck: Deck): Players {
        var initPhasedPlayers = players
        repeat(INIT_RECEIVE_CARD_COUNT) {
            initPhasedPlayers = receiveCardFromDeck(initPhasedPlayers, deck)
        }
        return Players(initPhasedPlayers)
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

    fun endPlayerTurn(player: Player): Players {
        return Players(updatePlayerStatus(player, player.turnOff()))
    }

    fun receiveCards(deck: Deck, turn: Turn): Players {
        var receivedCardPlayers = players.toList()
        for (player in players) {
            var target = player
            while (turn.isPlayerTurnOff(target)) {
                val result = receiveCard(target, deck)
                receivedCardPlayers = result.players.copy()
                target = result.player
                Game.showPlayerResult(target)
            }
        }
        return Players(receivedCardPlayers)
    }

    private fun receiveCardFromDeck(players: List<Player>, deck: Deck): Players {
        return Players(players.map { it.receiveCard(deck.drawCard()) })
    }

    private fun updatePlayerStatus(before: Player, after: Player): Players {
        return Players(players.replace(after) { it == before })
    }

    private fun receiveCard(player: Player, deck: Deck): PlayerResults {
        val receivedCardPlayer = player.receiveCard(deck.drawCard())
        return PlayerResults(updatePlayerStatus(player, receivedCardPlayer), receivedCardPlayer)
    }

    companion object {
        private const val MINIMUM_GAMER = 2
        private const val INIT_RECEIVE_CARD_COUNT = 2

        fun createGamers(names: Names): Players {
            return Players(names.names.map { Gamer(Profile(it)) })
        }
    }
}
