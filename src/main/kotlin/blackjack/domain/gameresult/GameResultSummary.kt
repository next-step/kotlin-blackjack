package blackjack.domain.gameresult

data class GameResultSummary(val playerResults: List<PlayerResult>) {
    fun dealerProfit(): Int = playerResults.sumOf { it.profit } * -1
}
