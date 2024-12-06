package blackjack

class ResultView {
    fun printBlackJackGameResult(blackJackGameResults: List<BlackJackGameResult>) {
        blackJackGameResults.forEach {
            val displayCards = it.cards.joinToString(", ") { drawCard -> drawCard.drawCardString }
            println("${it.playerName}: $displayCards - 결과: ${it.totalValue}")
        }
    }
}