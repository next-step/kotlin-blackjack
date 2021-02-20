package blackjack.domain.game

import blackjack.domain.player.Dealer
import blackjack.domain.player.Player

object GameResult {
    fun getPlayerResult(dealer: Dealer, player: Player): Boolean {
        if (player.isBust()) {
            return false
        }
        if (dealer.isBust()) {
            return true
        }
        return player.score() > dealer.score()
    }

    fun getPlayerProfit(dealer: Dealer, player: Player, rate: Double): Int {
        return player.getProfit(getPlayerResult(dealer, player), rate)
    }

    fun getDealerWinCounts(dealer: Dealer, players: List<Player>): Int {
        return players.filter { !getPlayerResult(dealer, it) }.count()
    }

    fun getDealerLoseCounts(dealer: Dealer, players: List<Player>): Int {
        return players.filter { getPlayerResult(dealer, it) }.count()
    }

    fun getDealerProfit(dealer: Dealer, players: List<Player>): Int {
        return players.map { getPlayerProfit(dealer, it, 1.0) }.sum()
    }
}
