package blackjack.domain.player

import blackjack.domain.card.Deck
import blackjack.domain.game.Bet
import blackjack.domain.game.Betting
import blackjack.domain.game.Bettings
import blackjack.domain.game.Credit
import blackjack.domain.game.GameResult
import blackjack.domain.game.Turn

fun <T> List<T>.replace(newValue: T, block: (T) -> Boolean): List<T> {
    return map {
        if (block(it)) newValue else it
    }
}

data class Players private constructor(val players: List<Player>) : List<Player> by players {

    init {
        require(players.size >= MINIMUM_GAMER)
        require(players.count { it is Dealer } == DEALER_COUNT)
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

    fun getBettings(bet: Bet): List<Betting> {
        val gammers = getGamers().map { Betting(it, Credit.from(bet.getPlayerBetting(it))) }
        val dealer = Betting(getDealer()!!, Credit.from(0))
        return gammers + dealer
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

    fun getResult(bettings: Bettings): GameResult {
        val dealer = getDealer() as Dealer
        val gamers = getGamers()
        return dealer.judge(bettings, gamers)
    }

    private fun getDealer(): Player? {
        return players.find { it is Dealer }
    }

    private fun getGamers(): List<Player> {
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

        fun from(name: String): Players {
            val names = Names.generateNames(name)
            val players = createGamers(names) + Dealer.of()
            return Players(players)
        }

        fun from(players: List<Player>): Players {
            return Players(players)
        }

        private fun createGamers(names: Names): List<Player> {
            return names.names.map { Gamer(Profile(it)) }
        }
    }
}
