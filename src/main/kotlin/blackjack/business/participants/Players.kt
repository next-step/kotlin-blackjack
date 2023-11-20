package blackjack.business.participants

import blackjack.business.card.CardDesk
import blackjack.business.drawConditionStrategy.DrawConditionStrategy

class Players(allPlayers: List<Player>) {

    private val _players: List<Player> = allPlayers

    val allPlayers: List<Player> = _players.toList()

    init {
        require(allPlayers.size > 1) { "플레이어는 2명 이상이여야 가능합니다." }
    }

    fun forEachPlayer(onPlayerAction: (Player) -> Unit) = allPlayers.forEach(onPlayerAction)

    fun dealInitialCards(cardDesk: CardDesk, onPlayerAction: (Player) -> Unit) {
        forEachPlayer {
            val playerCards = cardDesk.startDraw()
            it.addCards(playerCards)
            onPlayerAction(it)
        }
    }

    fun executeCardDraws(
        cardDesk: CardDesk,
        conditionStrategy: DrawConditionStrategy,
        getCommand: (String) -> String,
        onPlayerAction: (Player) -> Unit
    ) {
        forEachPlayer {
            while (it.canDrawCard() && conditionStrategy.shouldDraw(it.name, getCommand)) {
                it.addCard(cardDesk.draw())
                onPlayerAction(it)
            }
        }
    }

    fun getNames(): List<String> = allPlayers.map { it.name }

    companion object {
        fun from(playerNames: List<String>): Players {
            return Players(playerNames.map { Player(it) })
        }
    }
}
