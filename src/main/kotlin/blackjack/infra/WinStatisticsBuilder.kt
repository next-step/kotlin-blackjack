package blackjack.infra

import blackjack.domain.Player
import blackjack.domain.Statistics
import blackjack.domain.StatisticsBuilder

data class WinStatistics(
    val wins: Int = 0,
    val losses: Int = 0,
    val draws: Int = 0,
) : Statistics {
    fun incrementWins(): WinStatistics {
        return copy(wins = wins + 1)
    }

    fun incrementLosses(): WinStatistics {
        return copy(losses = losses + 1)
    }

    fun incrementDraws(): WinStatistics {
        return copy(draws = draws + 1)
    }
}

data class PlayerWinStatistics(
    private val values: MutableMap<String, WinStatistics>,
) : Statistics {
    fun getStats(player: String): WinStatistics {
        return values.getOrPut(player) { WinStatistics() }
    }

    fun updateStats(player: String, winStatistics: WinStatistics) {
        values[player] = winStatistics
    }
}

class WinStatisticsBuilder : StatisticsBuilder<PlayerWinStatistics>() {
    private val playerWinStatistics = PlayerWinStatistics(mutableMapOf())

    private fun getOrCreateStats(player: Player): WinStatistics {
        return playerWinStatistics.getStats(player.name)
    }

    private fun incrementStats(player: Player, winStatistics: WinStatistics) {
        playerWinStatistics.updateStats(player.name, winStatistics)
    }

    override fun onWin(player: Player) {
        incrementStats(player, getOrCreateStats(player).incrementWins())
    }

    override fun onLose(player: Player) {
        incrementStats(player, getOrCreateStats(player).incrementLosses())
    }

    override fun onDraw(player: Player) {
        incrementStats(player, getOrCreateStats(player).incrementDraws())
    }

    override fun build(): PlayerWinStatistics {
        return playerWinStatistics
    }
}
