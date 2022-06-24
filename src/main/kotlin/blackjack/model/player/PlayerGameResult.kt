package blackjack.model.player

class PlayerGameResult(
    val player: Player,
    val winCount: Int,
    val lostCount: Int,
) {

    companion object {
        fun ofDealer(dealer: Player, otherPlayers: List<Player>): PlayerGameResult {
            val winCount = otherPlayers.count { dealer.isScoreGreaterThan(it) }
            val lostCount = otherPlayers.count { dealer.isScoreLessThan(it) }

            return PlayerGameResult(dealer, winCount, lostCount)
        }

        fun ofPlayer(dealer: Player, player: Player): PlayerGameResult {
            val winCount = listOf(player).count { player.isScoreGreaterThan(dealer) }
            val lostCount = listOf(player).count { player.isScoreLessThan(dealer) }

            return PlayerGameResult(player, winCount, lostCount)
        }
    }
}
