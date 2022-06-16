package blackjack.view

import blackjack.common.PlayerSummary

object OutputView {
    fun printStartingSummaries(playerSummaries: List<PlayerSummary>, numberOfStartingCards: Int) {
        val playerNames = playerSummaries.map { it.playerName }

        println()
        println("${playerNames.joinWithComma()}에게 ${numberOfStartingCards}장의 카드를 나누었습니다.")
        playerSummaries.forEach { printPlayerSummary(it) }
        println()
    }

    fun printFinalSummaries(playerSummaries: List<PlayerSummary>) {
        println()
        playerSummaries.forEach { printPlayerSummary(it, true) }
    }

    fun printPlayerSummary(summary: PlayerSummary, withTotal: Boolean = false) {
        val playerInfo = summary.playerName
        val cardsInfo = "카드: ${summary.playerCards.joinWithComma()}"
        val totalInfo = if (withTotal) " - 결과: ${summary.playerCardsTotal}" else ""

        println("${playerInfo}${cardsInfo}$totalInfo")
    }

    fun printDealerSummary(summary: PlayerSummary) {
        val thirdCardDrawn: Boolean = summary.playerCards.size == 3

        println()
        if (thirdCardDrawn) {
            println("딜러는 16이하라 한장의 카드를 더 받았습니다.")
        } else {
            println("딜러는 17이상이라 추가로 카드를 받지 않았습니다.")
        }
    }

    private fun List<String>.joinWithComma(): String = joinToString(", ")
}
