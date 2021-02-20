package blackjack.domain.game

import blackjack.domain.player.Dealer
import blackjack.domain.player.Player

class GameResult(private val game: Game) {
    fun getDealerProfit(players: List<Player>): Double {
        return players.map { calculatePlayerProfitFor(it) }.sum() * -1
    }

    fun calculatePlayerProfitFor(player: Player): Double {
        val rate = calculatePlayerProfitRate(game.dealer, player)
        return player.bettingMoney * rate
    }

    private fun calculatePlayerProfitRate(dealer: Dealer, player: Player): Double {
        if (player.isBlackJack() && !dealer.isBlackJack()) {
            return BLACK_JACK_RATE
        }
        if (player.isBust()) {
            return PLAYER_BUST_RATE
        }
        if (dealer.isBust()) {
            return NORMAL_RATE
        }
        if (game.dealer.score() > player.score()) {
            return PLAYER_LOST_RATE
        }
        return NORMAL_RATE
    }

    companion object {
        private const val BLACK_JACK_RATE = 1.5
        private const val NORMAL_RATE = 1.0
        private const val PLAYER_BUST_RATE = -1.0
        private const val PLAYER_LOST_RATE = -1.0
    }
}
