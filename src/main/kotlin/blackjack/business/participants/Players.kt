package blackjack.business.participants

import blackjack.business.card.CardDesk
import blackjack.business.drawConditionStrategy.DrawConditionStrategy

class Players(val allGamePlayers: List<GamePlayer>) {

    init {
        require(allGamePlayers.size > 1) { "플레이어는 2명 이상이여야 가능합니다." }
    }

    fun forEachPlayer(onPlayerAction: (GamePlayer) -> Unit) = allGamePlayers.forEach(onPlayerAction)

    fun dealInitialCards(cardDesk: CardDesk, onPlayerAction: (GamePlayer) -> Unit): Players {
        val players = allGamePlayers.map { it.addCards(cardDesk.startDraw()) }
        players.forEach(onPlayerAction)
        return Players(players)
    }

    fun executeCardDraws(
        cardDesk: CardDesk,
        conditionStrategy: DrawConditionStrategy,
        getCommand: (String) -> String,
        onPlayerAction: (GamePlayer) -> Unit
    ): Players {
        val players = allGamePlayers.map {
            var gamePlayer = it
            while (gamePlayer.canDrawCard() && conditionStrategy.shouldDraw(gamePlayer.name, getCommand)) {
                gamePlayer = gamePlayer.addCard(cardDesk.draw())
                onPlayerAction(gamePlayer)
            }
            gamePlayer
        }
        return Players(players)
    }

    fun getNames(): List<String> = allGamePlayers.map { it.name }

    fun getGameResult(dealer: Dealer): GameResult {
        val playerResults = allGamePlayers.map(dealer::getPlayerResult)
        val dealerResults = PlayerResult(Dealer.DEALER_NAME, playerResults.sumOf { -it.profitOrLoss })
        return GameResult(dealerResults, playerResults)
    }

    companion object {
        fun from(playerNames: List<String>): Players = Players(playerNames.map { GamePlayer(it) })
    }
}
