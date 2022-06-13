package blackjack.domain

data class GameStatus(
    val dealer: Dealer,
    val players: List<Player>,
) {

    fun toProfitResult(): List<ProfitResult> {
        val players = players.map { ProfitResult(it.name, getPlayerProfit(it)) }
        val dealer = ProfitResult(dealer.name, -players.sumOf { it.profit })

        return listOf(dealer) + players
    }

    private fun getPlayerProfit(player: Player): Double {
        val betAmount = player.hand.betAmount.toDouble()

        return when (GameResult.from(dealer.score, player.score)) {
            GameResult.PLAYER_BLACKJACK_WON -> betAmount * BLACKJACK_WIN_BONUS_RATE
            GameResult.PLAYER_WON -> betAmount
            GameResult.DEALER_WON -> -betAmount
            else -> 0.0
        }
    }

    companion object {
        private const val BLACKJACK_WIN_BONUS_RATE = 1.5
    }
}

data class ProfitResult(val name: String, val profit: Double)
