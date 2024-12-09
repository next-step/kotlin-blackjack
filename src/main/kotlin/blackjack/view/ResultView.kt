package blackjack.view

import blackjack.domain.BlackJackGameResult
import blackjack.domain.BlackJackGameResults
import blackjack.domain.DrawResult

class ResultView {
    fun printInitialDraw(initialDrawResults: List<DrawResult>) {
        val playerNames = initialDrawResults.joinToString(",") { it.playerName }
        println("${playerNames}에게 2장을 나누었습니다.")
        printInitialDrawResults(initialDrawResults)
    }

    private fun printInitialDrawResults(initialDrawResult: List<DrawResult>) {
        initialDrawResult.forEach { printInitialDrawResult(it) }
    }

    private fun printInitialDrawResult(drawResult: DrawResult) {
        if (drawResult.dealer) {
            println("딜러: ${CardExpression.of(drawResult.cards[0]).value}")
        } else {
            printDrawResult(drawResult)
        }
    }

    fun printDrawResults(drawResults: List<DrawResult>) {
        drawResults.forEach { printDrawResult(it) }
    }

    fun printDrawResult(drawResult: DrawResult) {
        if (drawResult.dealer) {
            println("딜러는 16이하라 한장의 카드를 더 받았습니다.")
            return
        }

        val displayCards = drawResult.cards.joinToString(", ") { drawCard -> CardExpression.of(drawCard).value }
        println("${drawResult.playerName}카드: $displayCards")
    }

    fun printBlackJackGameResults(blackJackGameResults: BlackJackGameResults) {
        printDrawAndScoreResults(blackJackGameResults)
        printWinningOrLoseResults(blackJackGameResults)
    }

    private fun printDrawAndScoreResults(blackJackGameResults: BlackJackGameResults) {
        blackJackGameResults.value.forEach {
            val displayCards = it.cards.joinToString(", ") { drawCard -> CardExpression.of(drawCard).value }
            println("${it.playerName}: $displayCards - 결과: ${it.totalValue}")
        }
        println()
    }

    private fun printWinningOrLoseResults(blackJackGameResults: BlackJackGameResults) {
        println("## 최종 승패")
        println("딜러: ${blackJackGameResults.winningOrLoseResult.dealerScore.winningScore}승 ${blackJackGameResults.winningOrLoseResult.dealerScore.loseScore}패")
        blackJackGameResults.winningOrLoseResult.playerWinningOrLose.forEach { (playerName, win) ->
            val result = if (win) "승" else "패"
            println("$playerName: $result")
        }
    }
}
