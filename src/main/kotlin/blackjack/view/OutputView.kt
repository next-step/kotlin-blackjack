package blackjack.view

import blackjack.common.PlayerSummary
import blackjack.common.ScoreSummary

interface OutputView {
    fun printStartingSummaries(playerSummaries: List<PlayerSummary>)

    fun printFinalSummaries(playerSummaries: List<PlayerSummary>)

    fun printPlayerSummary(summary: PlayerSummary, withTotal: Boolean = false)

    fun printDealerSummary(summary: PlayerSummary)

    fun printScoreSummary(scoreSummary: ScoreSummary)
}
