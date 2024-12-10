package blackjack.infra

import blackjack.domain.Statistics
import blackjack.domain.StatisticsBuilder


data class WinStatistics(
    val wins: Int = 0,
    val losses: Int = 0,
    val draws: Int = 0
) : Statistics


class WinStatisticsBuilder : StatisticsBuilder<WinStatistics>() {
    private var wins = 0
    private var losses = 0
    private var draws = 0

    override fun onWin() {
        wins++
    }

    override fun onLose() {
        losses++
    }

    override fun onDraw() {
        draws++
    }

    override fun build(): WinStatistics {
        return WinStatistics(wins, losses, draws)
    }
}