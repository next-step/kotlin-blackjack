package blackjack.business.participants

import blackjack.business.card.CardDesk
import blackjack.business.util.Money

class Dealer(playerCards: PlayerCards = PlayerCards()) : BasePlayer(DEALER_NAME, playerCards) {

    override fun canDrawCard(): Boolean {
        return playerCards.sum() < DEALER_DRAW_CONDITION
    }

    fun getPlayerResult(gamePlayer: GamePlayer): PlayerResult {
        if (gamePlayer.isBust()) {
            return PlayerResult(gamePlayer.name, gamePlayer.money.lose())
        }
        if (isBust()) {
            return PlayerResult(gamePlayer.name, gamePlayer.money * 2.0)
        }
        if (gamePlayer.score - score == 0) {
            return PlayerResult(gamePlayer.name, Money())
        }
        if (gamePlayer.score - score < 0) {
            return PlayerResult(gamePlayer.name, gamePlayer.money.lose())
        }
        if (gamePlayer.score - score > 0 && gamePlayer.cards.size == 2 && gamePlayer.score == 21) {
            return PlayerResult(gamePlayer.name, gamePlayer.money * 2.5)
        }
        return PlayerResult(gamePlayer.name, gamePlayer.money * 2.0)
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
