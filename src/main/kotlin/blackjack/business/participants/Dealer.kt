package blackjack.business.participants

import blackjack.business.card.CardDesk
import blackjack.business.util.GameResult

class Dealer(private val dealerCards: PlayerCards = PlayerCards()) : Player(DEALER_NAME, dealerCards) {

    override fun canDrawCard(): Boolean {
        return dealerCards.sum() < DEALER_DRAW_CONDITION
    }

    override fun isBust(): Boolean {
        return dealerCards.isBust()
    }

    fun getPlayerResult(player: Player): PlayerResult {
        if (isBust()) {
            return PlayerResult(player.name, GameResult.WIN)
        }
        if (player.isBust()) {
            return PlayerResult(player.name, GameResult.LOSE)
        }
        return when (player.score - dealerCards.sum()) {
            0 -> PlayerResult(player.name, GameResult.DRAW)
            in 1..Int.MAX_VALUE -> PlayerResult(player.name, GameResult.WIN)
            else -> PlayerResult(player.name, GameResult.LOSE)
        }
    }

    fun getDealerResult(target: Players): Map<GameResult, Int> {
        return target.allPlayers.map { getDealerResult(it.score) }.groupingBy { it }.eachCount()
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
        return when (target - dealerCards.sum()) {
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
