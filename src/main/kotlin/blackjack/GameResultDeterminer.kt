package blackjack

object GameResultDeterminer {
    fun setProfit(players: List<Player>, dealer: Dealer) {
        for (player in players) {
            val result = player vs dealer

            val playerProfit = player.getProfit(result.profitRate)
            player.calculateProfit(playerProfit)
            dealer.calculateProfit(playerProfit)
        }
    }
}
