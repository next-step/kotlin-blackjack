package blackjack.domain

import blackjack.domain.player.Player
import blackjack.domain.player.User

class GameResult(players: List<User>, dealer: User) {
    val players: Map<User, BettingMoney> = initPlayerResult(players, dealer)
    val dealer: BettingMoney = initDealerResult()

    private fun initDealerResult() = BettingMoneys(players.values.toList()).sum() * -1.0

    private fun initPlayerResult(players: List<User>, dealer: User) =
        players.map { Pair(it, calculateProfit(it, dealer)) }.toMap()

    private fun calculateProfit(_player: User, dealer: User): BettingMoney {
        val player = _player as Player
        if (dealer.score.isBust) {
            return player.bettingMoney * ResultType.WIN.profitRate
        }
        val resultType = player.score compareTo dealer.score
        return player.bettingMoney * resultType.profitRate
    }
}
