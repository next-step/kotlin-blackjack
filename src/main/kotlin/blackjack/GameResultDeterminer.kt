package blackjack

object GameResultDeterminer {
    fun setProfit(players: List<Player>, dealer: Dealer) {
        for (player in players) {
            val result = player vs dealer

            val playerProfit = getProfit(player.betAmount, result.profitRate)
            player.profit += playerProfit
            dealer.profit -= playerProfit
        }
    }

    private fun getProfit(betAmount: BetAmount, profitRate: Float): Int = (betAmount.amount * profitRate).toInt()
}
