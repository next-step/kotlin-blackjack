package blackjack.domain

class PlayerResult(
    val player: Player,
    val gameResult: GameResult
) {

    fun getProfit(): Int = gameResult.profit(player).getAmount()

    companion object {
        fun ofGamePlayers(dealer: Player, players: Players): List<PlayerResult> {
            return players.list.map { ofGamePlayer(dealer, it) }
        }

        fun ofGamePlayer(dealer: Player, player: Player): PlayerResult {
            val playerResult = GameResult.getGamePlayerResult(dealer, player)
            return PlayerResult(player, playerResult)
        }
    }
}
