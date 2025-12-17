package domain

class GameResult(
    val winners: Players,
    val losers: Players,
    val draws: Players,
) {
    companion object {
        fun of(
            dealer: Dealer,
            players: Players,
        ): GameResult {
            val winner: MutableList<Player> = mutableListOf()
            val loser: MutableList<Player> = mutableListOf()
            val drawer: MutableList<Player> = mutableListOf()
            players.forEach { player ->
                when {
                    player.isBust() -> loser.add(player)
                    dealer.isBust() -> winner.add(player)
                    player.calculateScore() > dealer.calculateScore() -> winner.add(player)
                    player.calculateScore() < dealer.calculateScore() -> loser.add(player)
                    else -> drawer.add(player)
                }
            }
            return GameResult(Players(winner), Players(loser), Players(drawer))
        }
    }
}
