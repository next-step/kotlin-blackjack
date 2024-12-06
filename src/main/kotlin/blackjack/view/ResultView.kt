package blackjack.view

import blackjack.domain.BlackJackGameResult

class ResultView {
    fun printBlackJackGameResult(blackJackGameResults: List<BlackJackGameResult>) {
        blackJackGameResults.forEach {
            val displayCards = it.cards.joinToString(", ") { drawCard -> CardExpression.of(drawCard).value }
            println("${it.playerName}: $displayCards - 결과: ${it.totalValue}")
        }
    }
}
