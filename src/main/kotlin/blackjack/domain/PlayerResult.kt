package blackjack.domain

class PlayerResult(
    val player: Player,
    private val profit: Profit
) {
    fun getProfit(): Int = profit.getAmount()

    companion object {
        fun ofGamePlayers(dealer: Dealer, players: Players): List<PlayerResult> {
            return players.list.map { ofGamePlayer(dealer, it) }
        }

        fun ofGamePlayer(dealer: Dealer, player: Player): PlayerResult {
            val playerResult = GameResult.getGamePlayerResult(dealer, player)
            return PlayerResult(player, playerResult.profit(player))
        }
    }
}
