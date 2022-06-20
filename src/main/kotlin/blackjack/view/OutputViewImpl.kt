package blackjack.view

import blackjack.Blackjack
import blackjack.common.PlayerSummary
import blackjack.common.ScoreSummary

object OutputViewImpl : OutputView {
    override fun printStartingSummaries(playerSummaries: List<PlayerSummary>) {
        val playerNames = playerSummaries.map { it.playerName }

        println()
        println("${playerNames.joinWithComma()}에게 ${Blackjack.numberOfStartingCards}장의 카드를 나누었습니다.")
        playerSummaries.forEach { printPlayerSummary(it) }
        println()
    }

    override fun printFinalSummaries(playerSummaries: List<PlayerSummary>) {
        println()
        playerSummaries.forEach { printPlayerSummary(it, true) }
    }

    override fun printPlayerSummary(summary: PlayerSummary, withTotal: Boolean) {
        val playerInfo = summary.playerName
        val cardsInfo = "카드: ${summary.playerCards.joinWithComma()}"
        val totalInfo = if (withTotal) " - 결과: ${summary.playerCardsTotal}" else ""

        println("${playerInfo}${cardsInfo}$totalInfo")
    }

    override fun printDealerSummary(summary: PlayerSummary) {
        val thirdCardDrawn: Boolean = summary.playerCards.size == 3

        println()
        if (thirdCardDrawn) {
            println("딜러는 16이하라 한장의 카드를 더 받았습니다.")
        } else {
            println("딜러는 17이상이라 추가로 카드를 받지 않았습니다.")
        }
    }

    override fun printScoreSummary(scoreSummary: ScoreSummary) {
        println()
        println("## 최종 승패")
        scoreSummary.values
            .map { println("${it.first}: ${it.second}") }
    }

    private fun List<String>.joinWithComma(): String = joinToString(", ")
}
