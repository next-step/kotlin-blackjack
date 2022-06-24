package blackjack.model.player

class PlayerGameResult(
    val player: Player,
    val winCount: Int,
    val lostCount: Int,
) {

    val playerName
        get() = player.name

    companion object {
        const val LOST_DECISION_BOUNDARY_SCORE = 21

        fun of(player: Player, other: Player): PlayerGameResult {
            if (other.isAllScoreGreaterThan(LOST_DECISION_BOUNDARY_SCORE)) {
                return ofWin(player)
            }

            if (player.isAllScoreGreaterThan(LOST_DECISION_BOUNDARY_SCORE)) {
                return ofLost(player)
            }

            if (player.beats(other, LOST_DECISION_BOUNDARY_SCORE)) {
                return ofWin(player)
            }

            if (other.beats(player, LOST_DECISION_BOUNDARY_SCORE)) {
                return ofLost(player)
            }

            return ofDraw(player)
        }

        private fun ofWin(player: Player): PlayerGameResult {
            return PlayerGameResult(player, 1, 0)
        }

        private fun ofLost(player: Player): PlayerGameResult {
            return PlayerGameResult(player, 0, 1)
        }

        private fun ofDraw(player: Player): PlayerGameResult {
            return PlayerGameResult(player, 0, 0)
        }
    }
}
