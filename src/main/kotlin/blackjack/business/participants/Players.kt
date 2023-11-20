package blackjack.business.participants

import blackjack.business.card.CardDesk
import blackjack.business.drawConditionStrategy.DrawConditionStrategy
import blackjack.business.util.Money

class Players(allGamePlayers: List<GamePlayer>) {

    private val _gamePlayers: List<GamePlayer> = allGamePlayers

    val allGamePlayers: List<GamePlayer> = _gamePlayers.toList()

    init {
        require(allGamePlayers.size > 1) { "플레이어는 2명 이상이여야 가능합니다." }
    }

    fun forEachPlayer(onPlayerAction: (GamePlayer) -> Unit) = allGamePlayers.forEach(onPlayerAction)

    fun dealInitialCards(cardDesk: CardDesk, onPlayerAction: (GamePlayer) -> Unit) {
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
        onPlayerAction: (GamePlayer) -> Unit
    ) {
        forEachPlayer {
            while (it.canDrawCard() && conditionStrategy.shouldDraw(it.name, getCommand)) {
                it.addCard(cardDesk.draw())
                onPlayerAction(it)
            }
        }
    }

    fun getNames(): List<String> = allGamePlayers.map { it.name }

    fun getGameResult(dealer: Dealer): GameResult {
        val playerResults = allGamePlayers.map(dealer::getPlayerResult)
        val dealerResults = PlayerResult(Dealer.DEALER_NAME, playerResults.map { it.money.lose() }.reduce(Money::plus))
        return GameResult(dealerResults, playerResults)
    }

    companion object {
        fun from(playerNames: List<String>): Players {
            return Players(playerNames.map { GamePlayer(it) })
        }
    }
}