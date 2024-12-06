package blackjack.view

import blackjack.domain.BlackJackGameResult
import blackjack.domain.DrawResult

class ResultView {
    fun printInitialDraw(inititalDrawResult: List<DrawResult>) {
        val playerNames = inititalDrawResult.joinToString(",") { it.playerName }
        println("${playerNames}에게 2장을 나누었습니다.")
        printDrawResult(inititalDrawResult)
    }

    fun printDrawResult(drawResult: List<DrawResult>) {
        drawResult.forEach {
            val displayCards = it.cards.joinToString(", ") { drawCard -> CardExpression.of(drawCard).value }
            println("${it.playerName}카드: $displayCards")
        }
    }

    fun printBlackJackGameResult(blackJackGameResults: List<BlackJackGameResult>) {
        blackJackGameResults.forEach {
            val displayCards = it.cards.joinToString(", ") { drawCard -> CardExpression.of(drawCard).value }
            println("${it.playerName}: $displayCards - 결과: ${it.totalValue}")
        }
    }
}
