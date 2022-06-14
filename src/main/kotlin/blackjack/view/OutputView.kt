package blackjack.view

import blackjack.common.PlayerSummary

object OutputView {
    fun printStartingPlayerSummaries(playerSummaries: List<PlayerSummary>, numberOfStartingCards: Int) {
        val playerNames = playerSummaries.map { it.playerName }

        println()
        println("${playerNames.joinWithComma()}에게 ${numberOfStartingCards}장의 카드를 나누었습니다.")
        playerSummaries.forEach { printPlayerSummary(it) }
        println()
    }

    fun printFinalPlayerSummaries(playerSummaries: List<PlayerSummary>) {
        println()
        playerSummaries.forEach { printPlayerSummary(it, true) }
    }

    fun printPlayerSummary(summary: PlayerSummary, withTotal: Boolean = false) {
        val playerInfo = summary.playerName
        val cardsInfo = "카드: ${summary.playerCards.joinWithComma()}"
        val totalInfo = if (withTotal) " - 결과: ${summary.playerCardsTotal}" else ""

        println("${playerInfo}${cardsInfo}$totalInfo")
    }

    private fun List<String>.joinWithComma(): String = joinToString(", ")
}
