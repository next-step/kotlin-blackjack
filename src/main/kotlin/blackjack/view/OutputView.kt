package blackjack.view

import blackjack.PlayerSummary

object OutputView {
    fun markStartOfNewParagraph() {
        println()
    }

    fun printPlayerNamesAndNumberOfCardsDrawn(playerNames: List<String>, numberOfStartingCards: Int) {
        println("${playerNames.joinWithComma()}에게 ${numberOfStartingCards}장의 카드를 나누었습니다.")
    }

    fun printPlayerSummaries(summaries: List<PlayerSummary>, withTotal: Boolean = false) {
        summaries.forEach { printPlayerSummary(it, withTotal) }
    }

    fun printPlayerSummary(summary: PlayerSummary, withTotal: Boolean = false) {
        val playerInfo = summary.playerName
        val cardsInfo = "카드: ${summary.playerCards.joinWithComma()}"
        val totalInfo = if (withTotal) " - 결과: ${summary.playerCardsTotal}" else ""

        println("${playerInfo}${cardsInfo}$totalInfo")
    }

    private fun List<String>.joinWithComma(): String = joinToString(", ")
}
