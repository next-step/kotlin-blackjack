package blackjack.business.participants

import blackjack.business.card.CardDesk
import blackjack.business.util.GameResult
import blackjack.business.util.Money

class Dealer(playerCards: PlayerCards = PlayerCards()) : BasePlayer(DEALER_NAME, playerCards) {

    override fun canDrawCard(): Boolean {
        return playerCards.sum() < DEALER_DRAW_CONDITION
    }

    fun getPlayerResult(gamePlayer: GamePlayer): PlayerResult {
        if (isBust()) {
            return PlayerResult(gamePlayer.name, GameResult.WIN)
        }
        if (gamePlayer.isBust()) {
            return PlayerResult(gamePlayer.name, GameResult.LOSE)
        }
        return when (gamePlayer.score - playerCards.sum()) {
            0 -> PlayerResult(gamePlayer.name, GameResult.DRAW)
            in 1..Int.MAX_VALUE -> PlayerResult(gamePlayer.name, GameResult.WIN)
            else -> PlayerResult(gamePlayer.name, GameResult.LOSE)
        }
    }

    fun getDealerResult(target: Players): Money {
        return target.allGamePlayers.map { getDealerResult(it) }.reduce(Money::plus)
    }

    fun executeCardDraws(cardDesk: CardDesk, announcer: () -> Unit) {
        if (canDrawCard()) {
            announcer()
            addCard(cardDesk.draw())
        }
    }

    private fun getDealerResult(player: GamePlayer): Money {
        if (isBust()) {
            return player.money.lose()
        }
        if (player.score > 21) {
            return player.money.lose()
        }
        return when (player.score - score) {
            0 -> Money()
            in 1..Int.MAX_VALUE -> player.money.lose()
            else -> player.money
        }
    }

    companion object {
        private const val DEALER_NAME = "딜러"
        private const val DEALER_DRAW_CONDITION = 17
    }
}
