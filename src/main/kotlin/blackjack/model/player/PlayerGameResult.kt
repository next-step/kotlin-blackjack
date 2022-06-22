package blackjack.model.player

class PlayerGameResult private constructor(
    val player: Player,
    val winCount: Int,
    val lostCount: Int,
) {

    companion object {
        fun of(player: Player, players: Players): PlayerGameResult {
            if (player.isDealer) {
                return generateDealerGameResult(player, players)
            }
            return generatePlayerGameResult(player, players)
        }

        private fun generateDealerGameResult(player: Player, players: Players): PlayerGameResult {
            val otherPlayers = players.players
                .filter { it != player }

            val winCount = otherPlayers.count { player.isScoreGreaterThan(it) }
            val lostCount = otherPlayers.count { player.isScoreLessThan(it) }

            return PlayerGameResult(player, winCount, lostCount)
        }

        private fun generatePlayerGameResult(player: Player, players: Players): PlayerGameResult {
            val dealer = players.players
                .firstOrNull { it.isDealer }

            require(dealer != null) { "딜러가 존재하지 않습니다." }

            val winCount = listOf(player).count { player.isScoreGreaterThan(dealer) }
            val lostCount = listOf(player).count { player.isScoreLessThan(dealer) }

            return PlayerGameResult(player, winCount, lostCount)
        }
    }
}
