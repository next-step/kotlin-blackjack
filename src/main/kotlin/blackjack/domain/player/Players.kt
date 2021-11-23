package blackjack.domain.player

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

        if (players.count { it is Dealer } > DEALER_COUNT) {
            throw IllegalStateException(DEALER_ALREADY_EXIST)
        }
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

    fun receiveCards(deck: Deck, turn: Turn, playerCallback: (Player) -> Unit, dealerCallback: () -> Unit): Players {
        var receivedCardPlayers = players.toList()
        players.forEach {
            receivedCardPlayers =
                updateReceiveCards(it, turn, receivedCardPlayers, deck, playerCallback, dealerCallback)
        }
        return Players(receivedCardPlayers)
    }

    private fun updateReceiveCards(
        player: Player,
        turn: Turn,
        players: List<Player>,
        deck: Deck,
        playerCallback: (Player) -> Unit,
        dealerCallback: () -> Unit
    ): List<Player> {
        var receivedCardPlayers = players
        var target = player
        while (canGamerHit(target, turn)) {
            val result = receiveCard(receivedCardPlayers, target, deck)
            receivedCardPlayers = result.players.copy()
            target = result.player
            playerCallback(target)
        }
        if (canDealerHit(target)) {
            val result = receiveCard(receivedCardPlayers, target, deck)
            receivedCardPlayers = result.players.copy()
            dealerCallback()
        }
        return receivedCardPlayers
    }

    fun addPlayer(player: Player): Players {
        return Players(players + player)
    }

    fun getPlayersByScore(): List<Player> {
        return players.sortedByDescending { it.getHighestPoint() }
    }

    fun getDealer(): Player? {
        return players.find { it is Dealer }
    }

    fun getGamers(): List<Player> {
        return players.filterIsInstance<Gamer>()
    }

    private fun canGamerHit(target: Player, turn: Turn) =
        target is Gamer && turn.isPlayerTurnOff(target) && target.canReceiveCard()

    private fun canDealerHit(target: Player) = target is Dealer && target.canReceiveCard()

    private fun receiveCardFromDeck(players: List<Player>, deck: Deck): Players {
        return Players(players.map { it.receiveCard(deck.drawCard()) })
    }

    private fun updatePlayerStatus(before: Player, after: Player): Players {
        return Players(players.replace(after) { it == before })
    }

    private fun updatePlayerStatus(players: List<Player>, before: Player, after: Player): Players {
        return Players(players.replace(after) { it == before })
    }

    private fun receiveCard(players: List<Player>, player: Player, deck: Deck): PlayerResults {
        val receivedCardPlayer = player.receiveCard(deck.drawCard())
        return PlayerResults(updatePlayerStatus(players, player, receivedCardPlayer), receivedCardPlayer)
    }

    companion object {
        private const val MINIMUM_GAMER = 2
        private const val INIT_RECEIVE_CARD_COUNT = 2
        private const val DEALER_COUNT = 1
        private const val DEALER_ALREADY_EXIST = "딜러는 한 명이상 존재할 수 없습니다"

        fun createPlayers(names: Names): Players {
            return Players(names.names.map { Gamer(Profile(it)) })
        }
    }
}
