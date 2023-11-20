package blackjack.business.participants

import blackjack.business.card.CardDesk
import blackjack.business.util.GameResult

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

    fun getDealerResult(target: Players): Map<GameResult, Int> {
        return target.allGamePlayers.map { getDealerResult(it.score) }.groupingBy { it }.eachCount()
    }

    fun executeCardDraws(cardDesk: CardDesk, announcer: () -> Unit) {
        if (canDrawCard()) {
            announcer()
            addCard(cardDesk.draw())
        }
    }

    private fun getDealerResult(target: Int): GameResult {
        if (isBust()) {
            return GameResult.WIN
        }
        if (target > 21) {
            return GameResult.WIN
        }
        return when (target - playerCards.sum()) {
            0 -> GameResult.DRAW
            in 1..Int.MAX_VALUE -> GameResult.LOSE
            else -> GameResult.WIN
        }
    }

    companion object {
        private const val DEALER_NAME = "딜러"
        private const val DEALER_DRAW_CONDITION = 17
    }
}
