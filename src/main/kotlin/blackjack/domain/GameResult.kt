package blackjack.domain

import blackjack.domain.player.Player
import blackjack.domain.player.User

class GameResult(players: List<User>, dealer: User) {
    val players: Map<User, BettingMoney> = initPlayerResult(players, dealer)
    val dealer
        get() = players.values.sumOf { it.money } * -1

    private fun initPlayerResult(players: List<User>, dealer: User) =
        players.map { Pair(it, calculateProfit(it, dealer)) }.toMap()

    private fun calculateProfit(_player: User, dealer: User): BettingMoney {
        val player = _player as Player
        if (dealer.calculateScore().isBust) {
            return player.bettingMoney.multiply(ResultType.WIN.profitRate)
        }
        val resultType = player.calculateScore().compareTo(dealer.calculateScore())
        return player.bettingMoney.multiply(resultType.profitRate)
    }
}
