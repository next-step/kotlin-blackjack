package blackjack.domain

import blackjack.domain.player.GameResult
import blackjack.domain.player.Player
import kotlin.math.abs

class PlayerResults(val results: List<PlayerResult>) {

    fun calcRevenues() {
        results.forEach { playerResult -> playerResult.calcRevenue() }
    }

    fun getDealerRevenue(): Long {
        val playersWinRevenue = abs(getSumOfRevenueFromGameResult(GameResult.LOOSE))
        val playersLooseRevenue = getSumOfRevenueFromGameResult(GameResult.WIN)
        return playersWinRevenue - playersLooseRevenue
    }

    private fun getSumOfRevenueFromGameResult(gameResult: GameResult): Long {
        return results.filter { it.result == gameResult }
            .map { it.player.betAmount.revenue }
            .fold(0) { acc, betAmount -> acc + betAmount }
    }
}

data class PlayerResult(
    val player: Player,
    val result: GameResult,
) {
    fun calcRevenue() {
        player.betAmount.calcRevenue(result, player.isBlackjack())
    }
}
